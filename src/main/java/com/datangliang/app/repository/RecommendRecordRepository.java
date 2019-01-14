package com.datangliang.app.repository;

import com.datangliang.app.domain.RecommendRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RecommendRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecommendRecordRepository extends JpaRepository<RecommendRecord, Long> {

}
