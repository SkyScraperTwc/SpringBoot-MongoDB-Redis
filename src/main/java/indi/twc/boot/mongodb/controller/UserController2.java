package indi.twc.boot.mongodb.controller;

import indi.twc.boot.mongodb.dao.UserMongoDaoRepository;
import indi.twc.boot.mongodb.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserController2")
public class UserController2 {

    @Autowired
    private UserMongoDaoRepository userMongoDaoRepository;

    @RequestMapping("/addUserEntity")
    public void addUserEntity() throws Exception {
        UserEntity user=new UserEntity();
        user.setUserName("bbb");
        user.setPassWord("789");
        userMongoDaoRepository.save(user);
    }

    @RequestMapping("/listAll")
    public List<UserEntity> listAll() throws Exception {
        List<UserEntity> users = userMongoDaoRepository.findAll();
        for(UserEntity user:users) {
            System.out.println(user);
        }
        return users;
    }


}
