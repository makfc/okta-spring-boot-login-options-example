package com.okta.springsecurityauth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    @RequestMapping("/")
    @ResponseBody
    public ResponseEntity<String> index(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String name = authentication.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://stackoverflow.com?" + name);
        request.logout();
        return new ResponseEntity<String>(null, headers, HttpStatus.FOUND);
    }

}