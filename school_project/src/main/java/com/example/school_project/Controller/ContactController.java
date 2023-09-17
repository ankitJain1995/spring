package com.example.school_project.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/contact")
    public String displayContactPage(){
        return "contact.html";
    }

    //Post Mapping through @RequestParam
    //@RequestParam binds the request and extract the data from query parameters from the URL
    @PostMapping("/saveMsg")
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email, @RequestParam String subject, @RequestParam String message){
        log.info("Name : "+name);
        log.info("Mobile : "+mobileNum);
        log.info("email : "+email);
        log.info("subject : "+subject);
        log.info("message : "+message);
        return new ModelAndView("redirect:/contact");
    }
}
