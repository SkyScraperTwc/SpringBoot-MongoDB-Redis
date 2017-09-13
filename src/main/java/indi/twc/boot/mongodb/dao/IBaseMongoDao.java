package indi.twc.boot.mongodb.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IBaseMongoDao<T> {
    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    List<T> find(Query query);
    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    T findOne(Query query);
    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    T update(Query query, Update update);

    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     * @return
     */
    void save(T entity);

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 通过ID删除记录
     *
     * @return
     */
    T remove(Query query);

    /**
     * 分页查询
     * @param page
     * @param query
     * @return
     */
//    public Page<T> findPage(Page<T> page, Query query);

    /**
     * 求数据总和
     * @param query
     * @return
     */
    long count(Query query);

    /**
     * getEntityClass()是T所对应的class对象
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 获取需要操作的集合collectionName名字
     * @return
     */
    String getCollectionName();
}
