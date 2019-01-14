package com.datangliang.app.repository;

import com.datangliang.app.domain.Isp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Isp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IspRepository extends JpaRepository<Isp, Long> {

}
