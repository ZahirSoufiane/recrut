package com.tnourji.recrut.service.impl;

import com.tnourji.recrut.model.QuizzResultCandidate;
import com.tnourji.recrut.repository.QuizzResultCandidateRepository;
import com.tnourji.recrut.service.QuizzResultCandidateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing QuizzResultCandidate.
 */
@Service
@Transactional
public class QuizzResultCandidateServiceImpl implements QuizzResultCandidateService{

    private final Logger log = LoggerFactory.getLogger(QuizzResultCandidateServiceImpl.class);

    private final QuizzResultCandidateRepository quizzResultCandidateRepository;

    public QuizzResultCandidateServiceImpl(QuizzResultCandidateRepository quizzResultCandidateRepository) {
        this.quizzResultCandidateRepository = quizzResultCandidateRepository;
    }

    /**
     * Save a quizzResultCandidate.
     *
     * @param quizzResultCandidate the entity to save
     * @return the persisted entity
     */
    @Override
    public QuizzResultCandidate save(QuizzResultCandidate quizzResultCandidate) {
        log.debug("Request to save QuizzResultCandidate : {}", quizzResultCandidate);
        return quizzResultCandidateRepository.save(quizzResultCandidate);
    }

    /**
     * Get all the quizzResultCandidates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuizzResultCandidate> findAll() {
        log.debug("Request to get all QuizzResultCandidates");
        return quizzResultCandidateRepository.findAll();
    }

    /**
     * Get one quizzResultCandidate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuizzResultCandidate findOne(Long id) {
        log.debug("Request to get QuizzResultCandidate : {}", id);
        return quizzResultCandidateRepository.findOne(id);
    }

    /**
     * Delete the quizzResultCandidate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuizzResultCandidate : {}", id);
        quizzResultCandidateRepository.delete(id);
    }
}
