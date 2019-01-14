package com.datangliang.app.repository;

import com.datangliang.app.domain.EnterpriseAuthRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnterpriseAuthRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnterpriseAuthRecordRepository extends JpaRepository<EnterpriseAuthRecord, Long> {

}
