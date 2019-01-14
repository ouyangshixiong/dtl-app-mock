package com.datangliang.app.repository;

import com.datangliang.app.domain.SiteInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SiteInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteInfoRepository extends JpaRepository<SiteInfo, Long> {

}
