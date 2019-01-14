package com.datangliang.app.repository;

import com.datangliang.app.domain.ProductRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRecordRepository extends JpaRepository<ProductRecord, Long> {

}
