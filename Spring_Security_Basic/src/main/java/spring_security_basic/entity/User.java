package spring_security_basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tab")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String roles;
}
