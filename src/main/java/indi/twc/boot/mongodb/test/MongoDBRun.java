package indi.twc.boot.mongodb.test;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

public class MongoDBRun {

    public MongoClient mongoClient;

    public MongoDatabase mongoDatabase;

    public MongoCollection<Document> mongoCollection;

    @Before
    public void before() throws UnknownHostException {
        // 连接到mongodb
        this.mongoClient = new MongoClient("localhost", 29000);

        // 打开数据库imooc
        this.mongoDatabase = mongoClient.getDatabase("imooc");

        this.mongoCollection = mongoDatabase.getCollection("imooc_collection");

    }

    @Test
    public void testQuery() throws UnknownHostException {
        // 查询
        FindIterable<Document> findIterable =  mongoCollection.find(Filters.eq("_id",new ObjectId("59845114536c0c07134dcd6f")));
        for (Document document :findIterable){
            System.out.println(document);
        }


    }

    @Test
    public void testAdd() throws UnknownHostException {
        // create
        Document document = new Document("name","杨俊明").
                                          append("sex","男").
                                          append("address",new BasicDBObject("postcode","201202").
                                                    append("street", "田林路888号").
                                                    append("city", "上海"));

        mongoCollection.insertOne(document);

    }

    @Test
    public void testUpdate() throws UnknownHostException {
        // update
        Document document = new Document();
        mongoCollection.updateMany(Filters.eq("_id",new ObjectId("59845e15536c0c07134dcd71")),
                new Document("$set",new Document("x",200)));
    }

    @Test
    public void testRemove() throws UnknownHostException {
        // delete
        mongoCollection.deleteMany(Filters.eq("x",14));

    }

    @Test
    public void testAdd2() throws UnknownHostException {
//        // 取得集合imooc_collection(若：imooc_collection不存在，mongodb将自动创建该集合)
//        DBCollection dbCollection = db.getCollection("imooc_collection");
//
//        // create
//        // 复杂对象
//        UserData userData = new UserData();
//        userData.setUserName("jimmy");
//        userData.setPassword("123456");
//
//        Set<String> pets = new HashSet<String>();
//        pets.add("cat");
//        pets.add("dog");
//
//        Map<String, String> favoriteMovies = new HashMap<String, String>();
//        favoriteMovies.put("dragons", "Dragons II");
//        favoriteMovies.put("avator", "Avator I");
//
//        userData.setFavoriteMovies(favoriteMovies);
//        userData.setPets(pets);
//        userData.setBirthday(new Date());
//
//        BasicDBObject basicDBObject = new BasicDBObject("key", "jimmy").append("value",toDBObject(userData));
//        dbCollection.insert(basicDBObject);
//        System.out.println(dbCollection.findOne(basicDBObject));

    }
}
