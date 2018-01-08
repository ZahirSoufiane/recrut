package com.tnourji.recrut.service.common;

/**
 * Concrete implementations of this interface are responsible for executing a few number of queries that can only be
 * executed in native SQL.
 * 
 * @author Michael Tnourji
 *         
 */
public interface NativeSQLService {
    
    /**
     * Resets the Oracle sequence responsible for generating the invoice & credit note numbers based on the year we are
     * in.
     * 
     * @param numberSequenceName
     *            the name of the sequence as it is defined in Oracle.
     */
    public void resetNumberSeq(String numberSequenceName);
    
    /**
     * Retrieves the next value of the specified Oracle sequence.
     * 
     * @param numberSequenceName
     *            the name of the Oracle sequence responsible for generating the next invoice/creditnote number.
     * @return a new number.
     */
    public Long getNumberSeqNextVal(String numberSequenceName);
    
    /**
     * Generates a new Eurekali tracking number by calling the nextVal on the appropriate Oracle sequence.
     * 
     * @return the newly generated Eurekali tracking number.
     */
    public Long getOrderIDSeqNextVal();
    
    /**
     * Generates a new user reference by calling the nextVal on the appropriate Oracle sequence.
     * 
     * @return the newly generated user reference.
     */
    public Long getUserIDSeqNextVal();
    
    /**
     * Generates a new B2B customer reference by calling the nextVal on the appropriate Oracle sequence.
     * 
     * @return the newly generated B2B customer reference.
     */
    public Long getCompanyIDSeqNextVal();
    
    /***
     * update fiscal years
     * 
     * @param firstAprilCountries
     *            country to update
     * @return number of updated rows
     */
    public int updateFiscalYears(String[] countries);
}
