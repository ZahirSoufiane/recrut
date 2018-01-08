package com.tnourji.recrut.repository;

import org.springframework.stereotype.Repository;

import com.tnourji.recrut.model.QuizzResultCandidate;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuizzResultCandidate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizzResultCandidateRepository extends JpaRepository<QuizzResultCandidate, Long> {

}
