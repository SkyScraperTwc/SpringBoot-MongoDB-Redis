package indi.twc.boot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    private String id;

    private String userName;

    private String passWord;
    @DBRef
    private Report report;
}
