package indi.twc.boot.mongodb.dao;

import indi.twc.boot.mongodb.constant.CollectionConst;
import indi.twc.boot.mongodb.dao.base.AbstractBaseMongoDao;
import indi.twc.boot.mongodb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserMongoDaoImpl extends AbstractBaseMongoDao<User> {

    @Override
    public Class<User> getEntityClass(){
        return User.class;
    }

    @Override
    public String getCollectionName() {
        return CollectionConst.MONGODATABASE_USER;
    }
}
