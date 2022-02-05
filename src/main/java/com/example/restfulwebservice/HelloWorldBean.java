package com.example.restfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data//모든 필드 getter, setter, toString 등 만들어줌
@AllArgsConstructor//모든 아규먼트 추가 생성자
@NoArgsConstructor//디폰트생성자추가
public class HelloWorldBean {
    private String message;
}
