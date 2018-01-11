package com.tnourji.recrut.repository;

import org.springframework.stereotype.Repository;

import com.tnourji.recrut.model.Condidate;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Condidat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CondidatRepository extends JpaRepository<Condidate, Long> {

}
