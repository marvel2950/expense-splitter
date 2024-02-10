package com.userms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_custom")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private int expense;
}
