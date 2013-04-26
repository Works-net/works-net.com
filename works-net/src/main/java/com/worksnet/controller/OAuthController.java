package com.worksnet.controller;

import com.worksnet.model.User;
import com.worksnet.model.oauth.GitHubAuth;
import com.worksnet.service.OAuthService;
import com.worksnet.service.UserService;
import com.worksnet.system.Conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: maxim.levicky
 * Date: 4/10/13
 * Time: 5:56 PM
 */
@Controller
public class OAuthController extends BaseController {

    @Autowired
    private OAuthService service;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/oauth/github", method = RequestMethod.GET)
    public String githubAuthPage(Model model, @RequestParam(value = "code", required = false) String code) {
        if (code != null) {
            try {
                GitHubAuth gitHubAuth = service.getGitHubLoginByCode(code);
                User user;
                if (UserService.isAuthorized()) {
                    user = UserService.getCurrentUser();
                } else {
                    user = UserService.createNewUser(OAuthService.generateLogin(gitHubAuth));
                    user.setEnabled(true);
                    userService.save(user);
                }
                gitHubAuth.setUserId(user.getId());
                service.addGitHubAuth(gitHubAuth);
            } catch (OAuthService.OAuthError oAuthError) {
//                result.rejectValue("OAuthError", "OAuth.cantAuth");
            }
        }
        model.addAttribute("clientId", Conf.get("github.clientId"));
        return "auth/oauth/github/auth";
    }


}
