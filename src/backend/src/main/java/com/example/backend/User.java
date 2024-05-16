package com.example.backend;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    public String username;
    public String password;
    private String name;
    private String gender;
    private Date birthDate;
    private String description;
}
