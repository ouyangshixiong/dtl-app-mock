package com.datangliang.app.repository;

import com.datangliang.app.domain.DTLUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DTLUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTLUserRepository extends JpaRepository<DTLUser, Long> {

}
