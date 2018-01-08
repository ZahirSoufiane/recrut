package com.tnourji.recrut.audit;

import java.util.Date;

/***
 * This interface is used for entities that needs to have updatedBy / updatedOn fields filled in
 * 
 * @author Michael Tnourji
 * 
 */
public interface AuditableUpdateEntity {
    
    /***
     * set modification user
     * 
     * @param modifiedBy
     *            modification user
     */
    public abstract void setModifiedBy(String modifiedBy);
    
    /***
     * set modification time
     * 
     * @param modifiedOn
     *            modification time
     */
    public abstract void setModifiedOn(Date modifiedOn);
}
