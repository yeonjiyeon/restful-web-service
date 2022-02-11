package com.example.restfulwebservice.helloworld;

import com.example.restfulwebservice.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController//@ResponseBody를 사용하지 않아도 반환값을 json으로 만들어줌
public class HelloWorldController {
    //GET
    //hello-world (endpoint)
    //사용자 요청이 오면 Hello World 값 지정
    //@RequestMapping(method=RequestMethod.GET, path="/hello-world")-> 과거 사용
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
        //String.format("표현하고자 하는 문자, 문자의 형태", 변수)
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false)Locale locale){
        //"Accept-Language"값이 포함되지 않았을 경우(false)  locale 값이 korea가 된다
        return messageSource.getMessage("greeting.message", null, locale);

    }
}
