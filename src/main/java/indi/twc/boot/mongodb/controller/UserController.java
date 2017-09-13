package indi.twc.boot.mongodb.controller;

import indi.twc.boot.mongodb.dao.UserMongoDao;
import indi.twc.boot.mongodb.entity.UserEntity;
import indi.twc.boot.mongodb.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserMongoDao userMongoDao;

    @RequestMapping("/addUserEntity")
    public void addUserEntity() throws Exception {
        UserEntity user=new UserEntity();
        user.setUserName("mmm");
        user.setPassWord("000000");
        userMongoDao.save(user);
    }

    @RequestMapping("/listAll")
    public List<UserEntity> listAll() throws Exception {
        Query query = new Query();
        return userMongoDao.find(query);
    }

    @RequestMapping("/findById/{id}")
    public UserEntity findById(@PathVariable String id) throws Exception {
        return userMongoDao.findById(id);
    }

    @RequestMapping("/findOne/{username}")
    public UserEntity findOne(@PathVariable String username) throws Exception {
        Query query = new Query(Criteria.where("userName").is(username));
        return userMongoDao.findOne(query);
    }

    @RequestMapping("/update/{username}")
    public UserEntity update(@PathVariable String username) throws Exception {
        Query query = new Query(Criteria.where("userName").is(username));
        //特殊更新，更新author为jason的数据，如果没有author为jason的数据则以此条件创建一条新的数据
        //update的rename方法用于修改key的名称
        //update的inc方法用于做累加操作，将money在之前的基础上加上100
        //更新条件不变，更新字段改成了一个我们集合中不存在的，用set方法如果更新的key不存在则创建一个新的key
        //update的unset方法用于删除key
        //update的pull方法用于删除tags数组中的java
        Update update = new Update().set("passWord", "123456");
        return userMongoDao.update(query, update);
    }

    @RequestMapping("/remove/{id}")
    public UserEntity remove(@PathVariable String id) throws Exception {
        Query query = new Query(Criteria.where("id").is(id));
        return userMongoDao.remove(query);
    }

    @RequestMapping("/count")
    public long count() throws Exception {
        Query query = new Query();
        return userMongoDao.count(query);
    }

    @RequestMapping("/getPage")
    public Pagination getPage() throws Exception {
        Query query = new Query();
        return userMongoDao.getPage(3,query);
    }
}
