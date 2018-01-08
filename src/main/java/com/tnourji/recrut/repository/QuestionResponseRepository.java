package com.tnourji.recrut.repository;

import org.springframework.stereotype.Repository;

import com.tnourji.recrut.model.QuestionResponse;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuestionResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {

}
