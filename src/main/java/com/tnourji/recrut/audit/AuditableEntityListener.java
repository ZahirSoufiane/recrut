package com.tnourji.recrut.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/***
 * Listen to entity implementing AuditableEntity Set creation date/time before persisting the entity to the DB
 * 
 * @author Michael Tnourji
 * 
 */
public abstract class AuditableEntityListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditableEntityListener.class);
    
    protected static final String UNKNOWN_USER = "UNKNOWN";
    
    /***
     * get connected user
     * 
     * @return connected user
     */
    protected String getUser() {
        String user;
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (null == auth) {
            LOGGER.error("The connected user couldn't be retrieved");
            user = UNKNOWN_USER;
        } else {
            user = auth.getName();
        }
        return user;
    }
}
