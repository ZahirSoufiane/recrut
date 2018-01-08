package com.tnourji.recrut.audit;

import java.util.Date;

import javax.persistence.PreUpdate;

/***
 * Listen to entity implementing AuditableEntity Set modification date/time before updating the entity to the DB
 * 
 * @author Michael Tnourji
 * 
 */
public class AuditableUpdateEntityListener extends AuditableEntityListener {
    
    /***
     * set audit information before update
     * 
     * @param entity
     *            entity to update
     */
    @PreUpdate
    public void beforeUpdate(AuditableUpdateEntity entity) {
        entity.setModifiedBy(getUser());
        entity.setModifiedOn(new Date());
    }
    
}
