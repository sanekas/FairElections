package edu.infosec.fairelections.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHomePage() {
        return "<h1 align=\"center\">Welcome to fair elections!</h1>";
    }

}