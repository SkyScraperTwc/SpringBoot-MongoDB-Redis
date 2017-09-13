package indi.twc.boot.mongodb.dao;

import indi.twc.boot.mongodb.constant.CollectionConst;
import indi.twc.boot.mongodb.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserMongoDao extends BaseMongoDao<UserEntity> {

    @Override
    public Class<UserEntity> getEntityClass(){
        return UserEntity.class;
    }

    @Override
    public String getCollectionName() {
        return CollectionConst.MONGODATABASE_USERENTITY;
    }
}
