package com.okta.springsecurityauth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class WebController {

    @RequestMapping("/")
//    @ResponseBody
    public ResponseEntity<String> index(Authentication authentication,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException {
        String name = authentication.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "https://localhost:4200?code=" + name);
//        request.logout();
        return new ResponseEntity<String>(null, headers, HttpStatus.FOUND);
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("clientId") Optional<String> clientIdOptional,
                            HttpServletRequest request,
                            HttpServletResponse response) throws ServletException {
        System.out.println("/authorize");
        request.logout();
        return "redirect:userMainPage";
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException {
        return new ResponseEntity<String>("{ }", HttpStatus.OK);
    }

    @PostMapping("/revoke")
    public ResponseEntity<String> revoke(HttpServletResponse response) throws ServletException {
        return new ResponseEntity<String>("{ }", HttpStatus.OK);
    }
}