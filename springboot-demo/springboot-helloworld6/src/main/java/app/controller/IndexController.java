package app.controller;

import app.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    private IndexService indexService;

    @GetMapping(value = "/index")
    public Map<String,Object> index(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        return map;
    }
}
