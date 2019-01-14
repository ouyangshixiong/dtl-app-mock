package com.datangliang.app.repository;

import com.datangliang.app.domain.RealnameAuthRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RealnameAuthRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RealnameAuthRecordRepository extends JpaRepository<RealnameAuthRecord, Long> {

}
