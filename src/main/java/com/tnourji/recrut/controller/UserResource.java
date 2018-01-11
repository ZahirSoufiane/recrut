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
import com.tnourji.recrut.model.Company;
import com.tnourji.recrut.model.User;
import com.tnourji.recrut.repository.UserRepository;
import com.tnourji.recrut.service.CompanyService;
import com.tnourji.recrut.service.CondidateService;
import com.tnourji.recrut.service.UserService;
import com.tnourji.recrut.util.HeaderUtil;

/**
 * REST controller for managing User.
 */
@RestController
@RequestMapping("/api")
public class UserResource {
    
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    
    private static final String ENTITY_NAME = "User";
    
    private final UserService userservice;
    
    private final CompanyService companyService;
    
    private final CondidateService condidateService;
    
    private UserRepository userRepository;
    
    public UserResource(UserService userservice, CompanyService companyService, CondidateService condidateService) {
        this.userservice = userservice;
        this.companyService = companyService;
        this.condidateService = condidateService;
    }
    
    /**
     * POST /users : Create a new User.
     *
     * @param User
     *            the User to create
     * @return the ResponseEntity with status 201 (Created) and with body the new User, or with status 400 (Bad Request)
     *         if the User has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    @Timed
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) {
            throw new BadRequestAlertException("A new User cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        
        
        User result = userservice.save(user);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * PUT /users : Updates an existing User.
     *
     * @param User
     *            the User to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated User, or with status 400 (Bad Request)
     *         if the User is not valid, or with status 500 (Internal Server Error) if the User couldn't be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/users")
    @Timed
    public ResponseEntity<User> updateUser(@RequestBody User User) throws URISyntaxException {
        log.debug("REST request to update User : {}", User);
        if (User.getId() == null) {
            // return createUser(User);
        }
        User result = userservice.save(User);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, User.getId().toString()))
                .body(result);
    }
    
    /**
     * GET /users : get all the users.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of users in body
     */
    @GetMapping("/users")
    @Timed
    public List<User> getAllusers() {
        log.debug("REST request to get all users");
        return userservice.findAll();
    }
    
    /**
     * GET /users/:id : get the "id" User.
     *
     * @param id
     *            the id of the User to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the User, or with status 404 (Not Found)
     */
    @GetMapping("/users/{id}")
    @Timed
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        User User = userservice.findOne(id);
        return null;// ResponseUtil.wrapOrNotFound(Optional.ofNullable(User));
    }
    
    /**
     * DELETE /users/:id : delete the "id" User.
     *
     * @param id
     *            the id of the User to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users/{id}")
    @Timed
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userservice.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
