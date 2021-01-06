package org.mentor.project.security.handler;

import org.mentor.project.model.Role;
import org.mentor.project.model.User;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = service.findByUsername(username);
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/users");
                break;
            }
            if (role.getRole().equals("ROLE_USER")) {
                response.sendRedirect("/user");
                break;
            }
        }
    }
}
