package com.projectSam.projects.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("/")
public class Controller {


    @RequestMapping("/api/test")
    public  String print()
    {
        return  "CALLED";

    }



    @RequestMapping("/get")
    public void getUrl(@RequestBody String string)
    {
        System.out.println(string);
    }


}
