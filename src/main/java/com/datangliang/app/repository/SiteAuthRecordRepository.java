package com.datangliang.app.repository;

import com.datangliang.app.domain.SiteAuthRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SiteAuthRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteAuthRecordRepository extends JpaRepository<SiteAuthRecord, Long> {

}
