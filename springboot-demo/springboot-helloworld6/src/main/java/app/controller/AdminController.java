package app.controller;

import app.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/admin/hello")
    public String admin(){
        return indexService.admin();
    }
}
