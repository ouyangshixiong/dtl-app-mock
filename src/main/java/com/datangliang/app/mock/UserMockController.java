/**
 * 
 */
package com.datangliang.app.mock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.User;
import com.datangliang.app.security.jwt.TokenProvider;
import com.datangliang.app.service.UserService;
import com.datangliang.app.web.rest.errors.InvalidPasswordException;
import com.datangliang.app.web.rest.vm.ManagedUserVM;
import com.datangliang.app.web.rest.vm.mock.MockLogin;
import com.datangliang.app.web.rest.vm.mock.MockRegister;
import com.datangliang.app.web.rest.vm.mock.MockResp;
import com.datangliang.app.web.rest.vm.mock.MockUser;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api")
public class UserMockController {
	
	private final Logger log = LoggerFactory.getLogger(UserMockController.class);
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private TokenProvider tokenProvider;

	@Autowired
    private AuthenticationManager authenticationManager;
	
    @PostMapping(value="/v1/user/login",produces="application/json")
    @Timed
    public String authorize(@RequestBody MockLogin mockLogin) throws JSONException {
        //并不真的去取数据
//        Optional<User> user = userService.getUserWithAuthoritiesByLogin(phone);
        MockUser mockUser = new MockUser();
        mockUser.nickname="mock nickname";
	      JSONObject resp = new MockResp("登录成功").toJson();
	      JSONObject response = new JSONObject();
	      String jwt = getJWT(mockLogin.phone,mockLogin.password);
	      response.put("access_token", jwt);
	      response.put("userInfo", mockUser.toJson());
	      resp.put("response", response);
	      return resp.toString();
    }

    @PostMapping(value="v1/user/register",produces="application/json")
    @Timed
    @ResponseStatus(HttpStatus.CREATED)
    public String registerAccount(@RequestBody MockRegister mockRegister) throws JSONException {
        if (!checkPasswordLength(mockRegister.getPassword())) {
            throw new InvalidPasswordException();
        }
        ManagedUserVM managedUserVM = new ManagedUserVM();
        managedUserVM.setLogin(mockRegister.getPhone());
        managedUserVM.setEmail(mockRegister.getPhone()+"@datangliang.com");
        managedUserVM.setPassword(mockRegister.getPassword());
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
          //并不真的去取数据
	      //Optional<User> user = userService.getUserWithAuthoritiesByLogin(phone);
	      MockUser mockUser = new MockUser();
	      mockUser.nickname="mock nickname";
	      
	      
	      JSONObject resp = new MockResp("注册成功").toJson();
	      JSONObject response = new JSONObject();
	      String jwt = getJWT(mockRegister.getPhone(),mockRegister.getPassword());
	      response.put("access_token", jwt);
	      response.put("userInfo", mockUser.toJson());
	      resp.put("response", response);
	      return resp.toString();
    }
    
    
    private String getJWT(String phone, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(phone, password);

            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);
            return jwt;
    }
    
    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
    
    

}
