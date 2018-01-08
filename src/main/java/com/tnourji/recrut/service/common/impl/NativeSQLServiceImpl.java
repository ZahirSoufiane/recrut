package com.tnourji.recrut.service.common.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tnourji.recrut.service.common.NativeSQLService;

/**
 * This class encapsulates the methods executing native sql statement by unwrapping the Hibernate session. Unwrapping
 * the Hibernate session is possible only in a method for which Spring opens an exclusive transaction (i.e. not a
 * transaction borrowed from the calling method) while Spring does not open a new transaction when a @Transactional
 * method calls another @Transactional method of the same class. Therefore putting these native sql executing methods in
 * the business classes such as InvoiceServiceImpl would cause exceptions as there are methods which would call them
 * internally (i.e. without creation of new proxies by Spring).
 * 
 * @author Michael Tnourji
 * 
 */

@Service("nativeSQLService")
public class NativeSQLServiceImpl implements NativeSQLService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void resetOldInvoiceNumberSeq(String invoiceNumberSequenceName) {
        Session session = getSession();
        SQLQuery dropSQL = session.createSQLQuery("DROP SEQUENCE "
                + invoiceNumberSequenceName);
        dropSQL.executeUpdate();
        session.flush();
        SQLQuery createSQL =
                session
                        .createSQLQuery("CREATE SEQUENCE "
                                + invoiceNumberSequenceName
                                + " MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH ? CACHE 20 NOORDER NOCYCLE");
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        createSQL.setParameter(1, (year * 1000) + 1000);
        createSQL.executeUpdate();
    }
    
    @Override
    @Transactional
    public void resetNumberSeq(String numberSequenceName) {
        Session session = getSession();
        SQLQuery dropSQL = session.createSQLQuery("DROP SEQUENCE " + numberSequenceName);
        dropSQL.executeUpdate();
        session.flush();
        SQLQuery createSQL =
                session
                        .createSQLQuery("CREATE SEQUENCE "
                                + numberSequenceName
                                + " MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE");
        createSQL.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Long getNumberSeqNextVal(String numberSequenceName) {
        Session session = getSession();
        SQLQuery sql = session.createSQLQuery("SELECT "
                + numberSequenceName + ".nextval FROM dual");
        List<BigDecimal> list = sql.list();
        return list.get(0).longValue();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Long getOrderIDSeqNextVal() {
        Session session = getSession();
        SQLQuery sql = session
                .createSQLQuery("SELECT ORDER_ID_SEQ.nextval FROM dual");
        List<BigDecimal> list = sql.list();
        return list.get(0).longValue();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Long getUserIDSeqNextVal() {
        Session session = getSession();
        SQLQuery sql = session
                .createSQLQuery("SELECT CLIENT_ID_SEQ.nextval FROM dual");
        List<BigDecimal> list = sql.list();
        return list.get(0).longValue();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Long getCompanyIDSeqNextVal() {
        Session session = getSession();
        SQLQuery sql = session
                .createSQLQuery("SELECT COMPANY_ID_SEQ.nextval FROM dual");
        List<BigDecimal> list = sql.list();
        return list.get(0).longValue();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public int updateFiscalYears(String[] countries) {
        Session session = getSession();
        
        SQLQuery sql = session.createSQLQuery(
                "UPDATE country SET FISCAL_YEAR = FISCAL_YEAR+1 WHERE SHORTNAME IN (:countries)");
        sql.setParameterList("countries", countries);
        return sql.executeUpdate();
    }
    
    /***
     * unwrap JPA em to get hibernate session
     * 
     * @return hibernate session
     */
    private Session getSession() {
        return em.unwrap(Session.class);
    }
}
