package com.oopcourse.careernote.controller;


import com.oopcourse.careernote.service.impl.EmailServiceImpl;
import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    private EmailServiceImpl mailService;


    @GetMapping("/sendMail")
    public String sendMail(){
        mailService.sendEmail("usedasaproxy38@gmail.com","Test1","my first java test mail!!!");
        return "mail sent sucessfully,please back to last page.";
    }



}
