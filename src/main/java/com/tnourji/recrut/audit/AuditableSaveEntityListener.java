package com.tnourji.recrut.audit;

import java.util.Date;

import javax.persistence.PrePersist;

/***
 * Listen to entity implementing AuditableEntity Set creation date/time before persisting the entity to the DB
 * 
 * @author Michael Tnourji
 * 
 */
public class AuditableSaveEntityListener extends AuditableEntityListener {
    
    /***
     * set audit information to the entity before its persisted to the database
     * 
     * @param entity
     *            the auditable entity
     */
    @PrePersist
    public void beforePersist(AuditableSaveEntity entity) {
        entity.setCreatedBy(getUser());
        entity.setCreatedOn(new Date());
    }
}
