package indi.twc.boot.mongodb.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private static final long serialVersionUID = -4770493237851400594L;

    private String userName;

    private String password;

    private Set<String> pets;

    private Map<String, String> favoriteMovies;

    private Date birthday;

}
