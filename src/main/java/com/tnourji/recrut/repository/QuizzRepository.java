package com.tnourji.recrut.repository;

import org.springframework.stereotype.Repository;

import com.tnourji.recrut.model.Quizz;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Quizz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizzRepository extends JpaRepository<Quizz, Long> {

}
