package com.datangliang.app.repository;

import com.datangliang.app.domain.StoreUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StoreUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

}
