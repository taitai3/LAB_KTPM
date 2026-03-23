package iuh.fit.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Horizontal partition - id chẵn
@Entity
@Table(name = "table_user_02")
public class UserTable02 extends UserPartition {
}
