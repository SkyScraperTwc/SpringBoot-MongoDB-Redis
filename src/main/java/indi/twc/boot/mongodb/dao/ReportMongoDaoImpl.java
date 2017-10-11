package indi.twc.boot.mongodb.dao;

import indi.twc.boot.mongodb.constant.CollectionConst;
import indi.twc.boot.mongodb.dao.base.AbstractBaseMongoDao;
import indi.twc.boot.mongodb.entity.Report;
import org.springframework.stereotype.Repository;

@Repository
public class ReportMongoDaoImpl extends AbstractBaseMongoDao<Report> {
    @Override
    public Class<Report> getEntityClass() {
        return Report.class;
    }

    @Override
    public String getCollectionName() {
        return CollectionConst.MONGODATABASE_REPORT;
    }
}
