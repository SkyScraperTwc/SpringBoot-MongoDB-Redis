package indi.twc.boot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable{
    private static final long serialVersionUID = -3258839839160856613L;

    @Id
    private String id;

    private String userName;

    private String passWord;
}
