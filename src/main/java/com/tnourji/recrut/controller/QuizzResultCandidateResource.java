package com.tnourji.recrut.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.QuizzResultCandidate;
import com.tnourji.recrut.service.QuizzResultCandidateService;
import com.tnourji.recrut.util.HeaderUtil;

/**
 * REST controller for managing QuizzResultCandidate.
 */
@RestController
@RequestMapping("/api")
public class QuizzResultCandidateResource {
    
    private final Logger log = LoggerFactory.getLogger(QuizzResultCandidateResource.class);
    
    private static final String ENTITY_NAME = "quizzResultCandidate";
    
    private final QuizzResultCandidateService quizzResultCandidateService;
    
    public QuizzResultCandidateResource(QuizzResultCandidateService quizzResultCandidateService) {
        this.quizzResultCandidateService = quizzResultCandidateService;
        
    }
    
    /**
     * POST /quizz-result-candidates : Create a new quizzResultCandidate.
     *
     * @param quizzResultCandidate
     *            the quizzResultCandidate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quizzResultCandidate, or with status
     *         400 (Bad Request) if the quizzResultCandidate has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/quizz-result-candidates")
    @Timed
    public ResponseEntity<QuizzResultCandidate> createQuizzResultCandidate(
            @RequestBody QuizzResultCandidate quizzResultCandidate)
        throws URISyntaxException {
        log.debug("REST request to save QuizzResultCandidate : {}", quizzResultCandidate);
        if (quizzResultCandidate.getId() != null) {
            throw new BadRequestAlertException("A new quizzResultCandidate cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        QuizzResultCandidate result = quizzResultCandidateService.save(quizzResultCandidate);
        return ResponseEntity.created(new URI("/api/quizz-result-candidates/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * PUT /quizz-result-candidates : Updates an existing quizzResultCandidate.
     *
     * @param quizzResultCandidate
     *            the quizzResultCandidate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quizzResultCandidate, or with status
     *         400 (Bad Request) if the quizzResultCandidate is not valid, or with status 500 (Internal Server Error) if
     *         the quizzResultCandidate couldn't be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/quizz-result-candidates")
    @Timed
    public ResponseEntity<QuizzResultCandidate> updateQuizzResultCandidate(
            @RequestBody QuizzResultCandidate quizzResultCandidate)
        throws URISyntaxException {
        log.debug("REST request to update QuizzResultCandidate : {}", quizzResultCandidate);
        if (quizzResultCandidate.getId() == null) {
            return createQuizzResultCandidate(quizzResultCandidate);
        }
        QuizzResultCandidate result = quizzResultCandidateService.save(quizzResultCandidate);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quizzResultCandidate.getId().toString()))
                .body(result);
    }
    
    /**
     * GET /quizz-result-candidates : get all the quizzResultCandidates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quizzResultCandidates in body
     */
    @GetMapping("/quizz-result-candidates")
    @Timed
    public List<QuizzResultCandidate> getAllQuizzResultCandidates() {
        log.debug("REST request to get all QuizzResultCandidates");
        return quizzResultCandidateService.findAll();
    }
    
    /**
     * GET /quizz-result-candidates/:id : get the "id" quizzResultCandidate.
     *
     * @param id
     *            the id of the quizzResultCandidate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quizzResultCandidate, or with status 404 (Not
     *         Found)
     */
    @GetMapping("/quizz-result-candidates/{id}")
    @Timed
    public ResponseEntity<QuizzResultCandidate> getQuizzResultCandidate(@PathVariable Long id) {
        log.debug("REST request to get QuizzResultCandidate : {}", id);
        QuizzResultCandidate quizzResultCandidate = quizzResultCandidateService.findOne(id);
        return null;// ResponseUtil.wrapOrNotFound(Optional.ofNullable(quizzResultCandidate));
    }
    
    /**
     * DELETE /quizz-result-candidates/:id : delete the "id" quizzResultCandidate.
     *
     * @param id
     *            the id of the quizzResultCandidate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quizz-result-candidates/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuizzResultCandidate(@PathVariable Long id) {
        log.debug("REST request to delete QuizzResultCandidate : {}", id);
        quizzResultCandidateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
