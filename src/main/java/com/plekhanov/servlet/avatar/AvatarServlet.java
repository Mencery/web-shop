package com.plekhanov.servlet.avatar;

import com.plekhanov.entity.user.User;
import com.plekhanov.service.AvatarService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.plekhanov.constant.listener.ListenerConstants.CURRENT_USER;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet that handles avatar picture
 */
@WebServlet(SLASH + AVATAR_SERVLET)
public class AvatarServlet extends HttpServlet {
    AvatarService avatarService;

    /**
     * initialisation avatar service
     */
    @Override
    public void init() {
        avatarService = new AvatarService();
    }

    /**
     * load picture
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException - avatar service throws
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        avatarService.loadAvatar(resp, ((User) req.getSession().getAttribute(CURRENT_USER)).getAvatarPath());
    }

    /**
     * save picture
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException - avatar service throws
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = ((User) req.getSession().getAttribute(CURRENT_USER)).getEmail();
        avatarService.saveImage(req, email);
        resp.sendRedirect(INDEX_PAGE);
    }
}
