package indi.twc.boot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    private String id;
    private String date;
    private String content;
    private String title;
}
