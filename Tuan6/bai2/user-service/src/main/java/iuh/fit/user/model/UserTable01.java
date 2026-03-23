package iuh.fit.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Horizontal partition - id lẻ
@Entity
@Table(name = "table_user_01")
public class UserTable01 extends UserPartition {
}
