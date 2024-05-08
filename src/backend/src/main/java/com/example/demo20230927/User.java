package com.example.demo20230927;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
//    private String username;
//    private String password;
    public String username;
    public String password;
    private String name;
    private String gender;
    private Date birthDate;
}
