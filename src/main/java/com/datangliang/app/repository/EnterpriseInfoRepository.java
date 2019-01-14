package com.datangliang.app.repository;

import com.datangliang.app.domain.EnterpriseInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnterpriseInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnterpriseInfoRepository extends JpaRepository<EnterpriseInfo, Long> {

}
