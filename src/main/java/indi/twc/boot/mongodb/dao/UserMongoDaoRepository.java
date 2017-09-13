package indi.twc.boot.mongodb.dao;

import indi.twc.boot.mongodb.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoDaoRepository extends MongoRepository<UserEntity,String> {

}
