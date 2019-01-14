package com.datangliang.app.repository;

import com.datangliang.app.domain.RealnameInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RealnameInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RealnameInfoRepository extends JpaRepository<RealnameInfo, Long> {

}
