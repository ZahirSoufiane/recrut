package com.tnourji.recrut.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

/***
 * db util classs
 * 
 * @author Michael Tnourji
 *         
 */
public final class DBUtil {
    /***
     * get blob from file
     * 
     * @param file file
     * @return blob data
     * @throws IOException io exception
     * @throws SQLException sql exception
     */
    public static Blob getBlobData(MultipartFile file) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        return new SerialBlob(bytes);
    }
}
