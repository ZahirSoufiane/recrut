package com.tnourji.recrut.service.impl;

import com.tnourji.recrut.model.Quizz;
import com.tnourji.recrut.repository.QuizzRepository;
import com.tnourji.recrut.service.QuizzService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Quizz.
 */
@Service
@Transactional
public class QuizzServiceImpl implements QuizzService{

    private final Logger log = LoggerFactory.getLogger(QuizzServiceImpl.class);

    private final QuizzRepository quizzRepository;

    public QuizzServiceImpl(QuizzRepository quizzRepository) {
        this.quizzRepository = quizzRepository;
    }

    /**
     * Save a quizz.
     *
     * @param quizz the entity to save
     * @return the persisted entity
     */
    @Override
    public Quizz save(Quizz quizz) {
        log.debug("Request to save Quizz : {}", quizz);
        return quizzRepository.save(quizz);
    }

    /**
     * Get all the quizzes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Quizz> findAll() {
        log.debug("Request to get all Quizzes");
        return quizzRepository.findAll();
    }

    /**
     * Get one quizz by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Quizz findOne(Long id) {
        log.debug("Request to get Quizz : {}", id);
        return quizzRepository.findOne(id);
    }

    /**
     * Delete the quizz by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quizz : {}", id);
        quizzRepository.delete(id);
    }
}
