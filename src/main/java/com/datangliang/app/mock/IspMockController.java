/**
 * 
 */
package com.datangliang.app.mock;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.Isp;
import com.datangliang.app.service.IspService;
import com.datangliang.app.web.rest.vm.mock.MockResp;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api")
public class IspMockController {
	
	private final Logger log = LoggerFactory.getLogger(IspMockController.class);
	
    private static final String ENTITY_NAME = "isp";

	@Autowired
    private IspService ispService;
	
    /**
     * POST  /isps : Create a new isp.
     *
     * @param isp the isp to create
     * @return the ResponseEntity with status 201 (Created) and with body the new isp, or with status 400 (Bad Request) if the isp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws JSONException 
     */
    @PostMapping(value="/v1/user/sendVerifyCode",produces="application/json")
    @Timed
    public String createIsp(@RequestBody String phone) throws URISyntaxException, JSONException {
        log.debug("REST request to save phone : {}", phone);
        Isp isp = new Isp();
        isp.setMobile(phone);
        isp.setCreateTime(Instant.now());
        Random random = new Random();
        isp.setTemplate(String.format("%6d", random.nextInt(1000000)));
        Isp result = ispService.save(isp);
        JSONObject resp = new MockResp("发送成功").toJson();
        JSONObject response = new JSONObject();
        response.put("verifyCode", isp.getTemplate());
        resp.put("response", response);
        return resp.toString();
    }

}
