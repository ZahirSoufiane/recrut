package com.tnourji.recrut.controller;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.util.HeaderUtil;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.Quizz;
import com.tnourji.recrut.service.QuizzService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Quizz.
 */
@RestController
@RequestMapping("/api")
public class QuizzResource {

    private final Logger log = LoggerFactory.getLogger(QuizzResource.class);

    private static final String ENTITY_NAME = "quizz";

    private final QuizzService quizzService;

    public QuizzResource(QuizzService quizzService) {
        this.quizzService = quizzService;
    }

    /**
     * POST  /quizzes : Create a new quizz.
     *
     * @param quizz the quizz to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quizz, or with status 400 (Bad Request) if the quizz has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quizzes")
    @Timed
    public ResponseEntity<Quizz> createQuizz(@RequestBody Quizz quizz) throws URISyntaxException {
        log.debug("REST request to save Quizz : {}", quizz);
        if (quizz.getId() != null) {
            throw new BadRequestAlertException("A new quizz cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Quizz result = quizzService.save(quizz);
        return ResponseEntity.created(new URI("/api/quizzes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quizzes : Updates an existing quizz.
     *
     * @param quizz the quizz to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quizz,
     * or with status 400 (Bad Request) if the quizz is not valid,
     * or with status 500 (Internal Server Error) if the quizz couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quizzes")
    @Timed
    public ResponseEntity<Quizz> updateQuizz(@RequestBody Quizz quizz) throws URISyntaxException {
        log.debug("REST request to update Quizz : {}", quizz);
        if (quizz.getId() == null) {
            return createQuizz(quizz);
        }
        Quizz result = quizzService.save(quizz);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quizz.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quizzes : get all the quizzes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quizzes in body
     */
    @GetMapping("/quizzes")
    @Timed
    public List<Quizz> getAllQuizzes() {
        log.debug("REST request to get all Quizzes");
        return quizzService.findAll();
        }

    /**
     * GET  /quizzes/:id : get the "id" quizz.
     *
     * @param id the id of the quizz to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quizz, or with status 404 (Not Found)
     */
    @GetMapping("/quizzes/{id}")
    @Timed
    public ResponseEntity<Quizz> getQuizz(@PathVariable Long id) {
        log.debug("REST request to get Quizz : {}", id);
        Quizz quizz = quizzService.findOne(id);
        return null;//ResponseUtil.wrapOrNotFound(Optional.ofNullable(quizz));
    }

    /**
     * DELETE  /quizzes/:id : delete the "id" quizz.
     *
     * @param id the id of the quizz to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quizzes/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuizz(@PathVariable Long id) {
        log.debug("REST request to delete Quizz : {}", id);
        quizzService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
