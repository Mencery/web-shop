package com.plekhanov.service;

import com.plekhanov.db.UsersDAO;
import com.plekhanov.servlet.avatar.AvatarServlet;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.avatar.AvatarConstants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * service for {@link AvatarServlet}
 */
public class AvatarService {

    /**
     * load avatar picture to index.page
     * @param resp - {@link HttpServletResponse}
     * @param avatarPath - path to picture
     * @throws IOException - ImageIO.read and  ImageIO.write throws
     */
    public void loadAvatar(HttpServletResponse resp, String avatarPath) throws IOException {
        BufferedImage bi = ImageIO.read(new File(AvatarConstants.AVATAR_DIRECTORY+avatarPath));
        ImageIO.write(bi, AvatarConstants.JPG, resp.getOutputStream());
    }

    /**
     * save avatar picture
     * @param request - {@link HttpServletRequest}
     * @param email - user id
     *
     */
    public void saveImage(HttpServletRequest request, String email){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);

        File tempDir = (File)request.getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    processUploadedFile(request, item, email + AvatarConstants.DOT_JPG);
                    addToDB(request, email, email + AvatarConstants.DOT_JPG);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void processUploadedFile(HttpServletRequest request, FileItem item,String fileName) throws Exception {
        File uploadedFile;

        String path = AvatarConstants.AVATAR_DIRECTORY + fileName;
        uploadedFile = new File(path);

        if (uploadedFile.exists()) {
            uploadedFile.delete();
            uploadedFile = new File(path);
        }


        uploadedFile.createNewFile();

        item.write(uploadedFile);
    }


    /**
     * add picture path to DB
     * @param request - {@link HttpServletRequest}
     * @param email - user id
     * @param fileName - picture path
     */
    private void addToDB(HttpServletRequest request, String email, String fileName){
        try {
            UsersDAO dao = (UsersDAO) request.getServletContext().getAttribute(ListenerConstants.CONTEXT_USERS_DAO);
            dao.updateAvatarPath(email, fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
