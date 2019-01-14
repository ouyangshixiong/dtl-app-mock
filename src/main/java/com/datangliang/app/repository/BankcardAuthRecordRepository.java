package com.datangliang.app.repository;

import com.datangliang.app.domain.BankcardAuthRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BankcardAuthRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankcardAuthRecordRepository extends JpaRepository<BankcardAuthRecord, Long> {

}
