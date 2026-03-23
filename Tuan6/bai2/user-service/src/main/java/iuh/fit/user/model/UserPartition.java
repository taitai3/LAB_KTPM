package iuh.fit.user.model;

import jakarta.persistence.*;
import lombok.Data;

// Model dùng chung cho table_user_01 và table_user_02
@Data
@MappedSuperclass
public class UserPartition {
    @Id
    private Integer id;
    private String name;
    private String email;
    private int age;
}
