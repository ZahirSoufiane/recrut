package com.tnourji.recrut.audit;

import java.util.Date;

/***
 * This interface is used for entities that needs to have createdBy / createdOn fields filled in
 * 
 * @author Michael Tnourji
 * 
 */
public interface AuditableSaveEntity extends AuditableEntity {
    
    /***
     * set creation user
     * 
     * @param createdBy
     *            creation user
     */
    public abstract void setCreatedBy(String createdBy);
    
    /***
     * set Creation time
     * 
     * @param createdOn
     *            creation time
     */
    public abstract void setCreatedOn(Date createdOn);
}
