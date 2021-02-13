package app.controller;

import app.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IndexService indexService;


    @GetMapping("/user/hello")
    public String user(){
        return indexService.user();
    }
}
