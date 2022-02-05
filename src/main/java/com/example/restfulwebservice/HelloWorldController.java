package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //GET
    //hello-world (endpoint)
    //사용자 요청이 오면 Hello World 값 지정
    //@RequestMapping(method=RequestMethod.GET, path="/hello-world")-> 과거 사용
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
