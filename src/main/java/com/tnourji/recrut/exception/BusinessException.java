package com.tnourji.recrut.exception;

/***
 * BusinessException class
 * 
 * @author Michael Tnourji
 *         
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /***
     * Exception
     * 
     * @param msg
     *            error message
     */
    public BusinessException(String msg) {
        super(msg);
    }
    
    /***
     * Exception
     * 
     * @param e
     *            exception
     * @param msg
     *            error message
     */
    public BusinessException(String msg, Exception e) {
        super(msg, e);
    }
}
