package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController//@ResponseBody를 사용하지 않아도 반환값을 json으로 만들어줌
public class HelloWorldController {
    //GET
    //hello-world (endpoint)
    //사용자 요청이 오면 Hello World 값 지정
    //@RequestMapping(method=RequestMethod.GET, path="/hello-world")-> 과거 사용
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }
}
