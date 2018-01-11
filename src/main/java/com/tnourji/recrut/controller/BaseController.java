package com.tnourji.recrut.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tnourji.recrut.model.User;
//import com.tnourji.recrut.security.InnovaSpringUser;
//import com.tnourji.recrut.security.UserAuthentication;
import com.tnourji.recrut.util.ApplicationConstants;
import com.tnourji.recrut.util.StringUtil;

/***
 * base controller
 * 
 * @author Michael Tnourji
 * 
 */
public abstract class BaseController {
    protected static final String CLASS_MENU_HIGHLIGHT = "mySelectedClass";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    
    private static final String JSON_DATE = "yyyy-MM-dd HH:mm:ss";
    
    private static final String JSON_DATE_ONLY = "yyyy-MM-dd";
    
    @Value("${photos.partners.url}")
    protected String photosUrl;
    
    @Value("${photos.partners.dir}")
    protected String photosPartnerDir;
    
    @Value("${profile.picture.dir}")
    protected String photoDir;
    
    /**
     * get navigation lang
     * 
     * @param lang
     *            language
     * @return language
     */
    protected String getNavigationLang(String lang) {
        String lowerLang;
        if (StringUtils.isEmpty(lang) || !ApplicationConstants.SUPPORTED_LANGUAGES.contains(lang.toLowerCase())) {
            LOGGER.error("THE LANGUAGE IS NOT SUPPORTED : " + lang);
            lowerLang = "en";
        } else {
            lowerLang = lang.toLowerCase();
        }
        return lowerLang;
    }
    
    /***
     * check if user is logged in
     * 
     * @return true if logged in
     */
    protected boolean isLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                // when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }
    
    /**
     * get user or null of not auth
     * 
     * @return user
     */
    /*
     * protected User getUserFromSecurityContext() { InnovaSpringUser user; try { user = ((UserAuthentication)
     * SecurityContextHolder.getContext().getAuthentication()) .getDetails(); } catch (Exception e) { return null; }
     * return user.getUser(); }
     */
    
    /**
     * update user from security context
     * 
     * @param wUser
     *            user to update
     */
    protected void updateUserFromSecurityContext(User wUser) {
        try {
            // UserAuthentication auth = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication());
            // InnovaSpringUser user = auth.getDetails();
            // user.setUser(wUser);
            // SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            LOGGER.error("no user found " + e.getMessage(), e);
        }
    }
    
    /***
     * get current user website
     * 
     * @return website
     */
    /*
     * protected Website getCurrentUserSite() { return ((User) getUserFromSecurityContext()).getWebsite(); }
     */
    
    /**
     * get current user login
     * 
     * @return login
     */
    /*
     * protected String getCurrentUserLogin() { String login = null; User user = getUserFromSecurityContext(); if (null
     * != user) { login = user.getEmail(); } return login; }
     */
    
    /***
     * upload
     * 
     * @param filename
     *            file name
     * @param file
     *            file
     * @param request
     *            request
     * @param photosDir
     *            photo directory where to store
     * @param photoSessionName
     *            photo session name to use
     * @param isMulti
     *            true if multiple files are allowed
     * @return response
     */
    protected String uploadAndGet(String filename, MultipartFile file, HttpServletRequest request, String photosDir,
            String photoSessionName, boolean isMulti) {
        
        if (!file.isEmpty()) {
            String name = filename;
            if (null == filename) {
                name = file.getOriginalFilename();
            }
            name = StringUtil.getRandomString(5) + "_" + name;
            
            try {
                byte[] bytes = file.getBytes();
                
                // Creating the directory to store file
                String rootPath = photosDir;
                File dir = new File(rootPath + File.separator + "temp");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Create the file on server
                String picturePath = dir.getAbsolutePath() + File.separator + name;
                File serverFile = new File(picturePath);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                if (isMulti) {
                    savePicturesInSession(request, photoSessionName, name);
                } else {
                    savePictureInSession(request, photoSessionName, name);
                }
                return loadFile(serverFile);
                
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + filename
                    + " because the file was empty.";
        }
    }
    
    /***
     * move temp file
     * 
     * @param image
     *            image
     */
    protected void moveFile(String image) {
        Path pathFrom = Paths.get(photoDir, "temp", image);
        Path pathTo = Paths.get(photoDir, image);
        try {
            Files.move(pathFrom, pathTo);
        } catch (IOException e) {
            LOGGER.error("file not found ! " + e.getMessage(), e);
        }
    }
    
    /**
     * load a file to a byte array
     * 
     * @param file
     *            file
     * @return byte array
     * @throws IOException
     *             exception
     */
    protected String loadFile(File file) throws IOException {
        try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            String encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
            return encodedBase64;
        }
    }
    
    /**
     * save picture in session
     * 
     * @param pictureSessionName
     *            picture session name
     * @param request
     *            request
     * @param picturePath
     *            path
     */
    private void savePictureInSession(HttpServletRequest request, String pictureSessionName, String picturePath) {
        request.getSession().setAttribute(pictureSessionName, picturePath);
    }
    
    /**
     * save pictures in session
     * 
     * @param pictureSessionName
     *            picture session name
     * @param request
     *            request
     * @param picturePath
     *            path
     */
    @SuppressWarnings("unchecked")
    private void savePicturesInSession(HttpServletRequest request, String pictureSessionName, String picturePath) {
        Object attr = request.getSession().getAttribute(pictureSessionName);
        List<String> savedFiles = new ArrayList<>();
        if (null != attr) {
            savedFiles = (List<String>) attr;
        }
        savedFiles.add(picturePath);
        request.getSession().setAttribute(pictureSessionName, savedFiles);
    }
}
