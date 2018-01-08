package com.tnourji.recrut.repository;

import org.springframework.stereotype.Repository;

import com.tnourji.recrut.exception.BusinessException;
import com.tnourji.recrut.model.User;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Utilisateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRepository  extends JpaRepository<User, Long>  {
	
//    /***
//     * fetch by token
//     * 
//     * @param token
//     *            token
//     * @return user
//     * @throws BusinessException
//     *             token not found
//     */
//    User fetchByToken(String token) throws BusinessException;
	
	@Query("select u from User u where u.id=:x")
	public User getUserById(@Param("x") Long id);

}
