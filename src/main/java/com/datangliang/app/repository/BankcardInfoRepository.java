package com.datangliang.app.repository;

import com.datangliang.app.domain.BankcardInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BankcardInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankcardInfoRepository extends JpaRepository<BankcardInfo, Long> {

}
