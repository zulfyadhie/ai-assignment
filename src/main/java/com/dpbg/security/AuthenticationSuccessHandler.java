package com.dpbg.security;

import com.dpbg.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zulfy on 9/12/16.
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserDao appUserDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Redirect to Dashboard Main Page
        SavedRequest savedRequest
                = new HttpSessionRequestCache().getRequest(request, response);

        String baseUrl = ServletUriComponentsBuilder.fromContextPath(request).build().toString();

        HttpSession session = request.getSession(false);
        session.setAttribute("authUsername", appUserDao.findOneByUsername(authentication.getName()));

        if (savedRequest == null || savedRequest.getRedirectUrl().equals(baseUrl) || savedRequest.getRedirectUrl().equals(baseUrl + "/")) {
            response.sendRedirect(request.getContextPath() + "/product");
        } else {
            response.sendRedirect(savedRequest.getRedirectUrl());
        }
    }
}