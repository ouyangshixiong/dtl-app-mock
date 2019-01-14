package com.datangliang.app.repository;

import com.datangliang.app.domain.UserLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {

}
