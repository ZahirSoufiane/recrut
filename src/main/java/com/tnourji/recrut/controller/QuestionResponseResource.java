package com.tnourji.recrut.controller;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.util.HeaderUtil;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.QuestionResponse;
import com.tnourji.recrut.service.QuestionResponseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuestionResponse.
 */
@RestController
@RequestMapping("/api")
public class QuestionResponseResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResponseResource.class);

    private static final String ENTITY_NAME = "questionResponse";

    private final QuestionResponseService questionResponseService;

    public QuestionResponseResource(QuestionResponseService questionResponseService) {
        this.questionResponseService = questionResponseService;
    }

    /**
     * POST  /question-responses : Create a new questionResponse.
     *
     * @param questionResponse the questionResponse to create
     * @return the ResponseEntity with status 201 (Created) and with body the new questionResponse, or with status 400 (Bad Request) if the questionResponse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/question-responses")
    @Timed
    public ResponseEntity<QuestionResponse> createQuestionResponse(@RequestBody QuestionResponse questionResponse) throws URISyntaxException {
        log.debug("REST request to save QuestionResponse : {}", questionResponse);
        if (questionResponse.getId() != null) {
            throw new BadRequestAlertException("A new questionResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionResponse result = questionResponseService.save(questionResponse);
        return ResponseEntity.created(new URI("/api/question-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /question-responses : Updates an existing questionResponse.
     *
     * @param questionResponse the questionResponse to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated questionResponse,
     * or with status 400 (Bad Request) if the questionResponse is not valid,
     * or with status 500 (Internal Server Error) if the questionResponse couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/question-responses")
    @Timed
    public ResponseEntity<QuestionResponse> updateQuestionResponse(@RequestBody QuestionResponse questionResponse) throws URISyntaxException {
        log.debug("REST request to update QuestionResponse : {}", questionResponse);
        if (questionResponse.getId() == null) {
            return createQuestionResponse(questionResponse);
        }
        QuestionResponse result = questionResponseService.save(questionResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, questionResponse.getId().toString()))
            .body(result);
    }

    /**
     * GET  /question-responses : get all the questionResponses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of questionResponses in body
     */
    @GetMapping("/question-responses")
    @Timed
    public List<QuestionResponse> getAllQuestionResponses() {
        log.debug("REST request to get all QuestionResponses");
        return questionResponseService.findAll();
        }

    /**
     * GET  /question-responses/:id : get the "id" questionResponse.
     *
     * @param id the id of the questionResponse to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the questionResponse, or with status 404 (Not Found)
     */
    @GetMapping("/question-responses/{id}")
    @Timed
    public ResponseEntity<QuestionResponse> getQuestionResponse(@PathVariable Long id) {
        log.debug("REST request to get QuestionResponse : {}", id);
        QuestionResponse questionResponse = questionResponseService.findOne(id);
        return null;//ResponseUtil.wrapOrNotFound(Optional.ofNullable(questionResponse));
    }

    /**
     * DELETE  /question-responses/:id : delete the "id" questionResponse.
     *
     * @param id the id of the questionResponse to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/question-responses/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuestionResponse(@PathVariable Long id) {
        log.debug("REST request to delete QuestionResponse : {}", id);
        questionResponseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
