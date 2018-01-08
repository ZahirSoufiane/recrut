package com.tnourji.recrut.service.impl;

import com.tnourji.recrut.model.QuestionResponse;
import com.tnourji.recrut.repository.QuestionResponseRepository;
import com.tnourji.recrut.service.QuestionResponseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing QuestionResponse.
 */
@Service
@Transactional
public class QuestionResponseServiceImpl implements QuestionResponseService{

    private final Logger log = LoggerFactory.getLogger(QuestionResponseServiceImpl.class);

    private final QuestionResponseRepository questionResponseRepository;

    public QuestionResponseServiceImpl(QuestionResponseRepository questionResponseRepository) {
        this.questionResponseRepository = questionResponseRepository;
    }

    /**
     * Save a questionResponse.
     *
     * @param questionResponse the entity to save
     * @return the persisted entity
     */
    @Override
    public QuestionResponse save(QuestionResponse questionResponse) {
        log.debug("Request to save QuestionResponse : {}", questionResponse);
        return questionResponseRepository.save(questionResponse);
    }

    /**
     * Get all the questionResponses.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponse> findAll() {
        log.debug("Request to get all QuestionResponses");
        return questionResponseRepository.findAll();
    }

    /**
     * Get one questionResponse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuestionResponse findOne(Long id) {
        log.debug("Request to get QuestionResponse : {}", id);
        return questionResponseRepository.findOne(id);
    }

    /**
     * Delete the questionResponse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionResponse : {}", id);
        questionResponseRepository.delete(id);
    }
}
