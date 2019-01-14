package com.datangliang.app.repository;

import com.datangliang.app.domain.StoreAuthRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StoreAuthRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreAuthRecordRepository extends JpaRepository<StoreAuthRecord, Long> {

}
