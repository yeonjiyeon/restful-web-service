package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    //생성자 통한 의존성 주입
    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();//바로 반환값을 호출
    }

    // GET /users/1 or / users/10 -> String
    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //HATEOAS
        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        //retrieveAllUsers 링크 추가
        entityModel.add(linkTo.withRel("all-users"));//해당 이름으로 링크가 추가된다
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        
        //사용자한테 요청값 변환해 주기
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id,@RequestBody User user){
        user = service.updateById(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
