package indi.twc.boot.mongodb.dao.base;

import indi.twc.boot.mongodb.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public abstract class AbstractBaseMongoDao<T> implements IBaseMongoDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<T> find(Query query) {
        System.out.println("--------------"+this.getEntityClass());
        System.out.println("--------------"+this.getCollectionName());
        return mongoTemplate.find(query, this.getEntityClass(), this.getCollectionName());
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass(), this.getCollectionName());
    }

    @Override
    public T update(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, this.getEntityClass(), this.getCollectionName());
    }

    @Override
    public void save(T entity) {
        mongoTemplate.insert(entity, this.getCollectionName());
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass(), this.getCollectionName());
    }

    @Override
    public T remove(Query query) {
         return mongoTemplate.findAndRemove(query, this.getEntityClass(), this.getCollectionName());
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, this.getEntityClass(), this.getCollectionName());
    }

    /**
     * 通过条件查询,查询分页结果
     */
    public Pagination<T> getPage(int currentPage, Query query) {
        final int PAGE_SIZE = 2;

        //获取总条数
        int totalCount = (int) this.count(query);

        int skip = (currentPage-1)*PAGE_SIZE;

        // skip相当于从那条记录开始
        query.skip(skip);

        // 从skip开始,取PAGE_SIZE条记录
        query.limit(PAGE_SIZE);

        List<T> dataList = this.find(query);

        Pagination<T> pagination = new Pagination<T>(PAGE_SIZE, currentPage, totalCount);

        pagination.build(dataList);//获取数据

        return pagination;
    }

}

