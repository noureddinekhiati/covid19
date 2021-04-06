package com.covid19;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class covid19Controller {
    private String appMode;
    @Autowired
    public covid19Controller(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String home(Model model){
        return "index";
    }

    @RequestMapping("/index.html")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/about.html")
    public String about(Model model){
        return "about";
    }

    @RequestMapping("/contact.html")
    public String contact(Model model){
        return "contact";
    }

}
