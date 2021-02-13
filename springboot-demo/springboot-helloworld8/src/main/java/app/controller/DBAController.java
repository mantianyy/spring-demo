package app.controller;

import app.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBAController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/db/hello")
    public String dba(){
        return indexService.dba();
    }
}
