package com.oopcourse.careernote.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends AbstractEntity{
    public User(){

    }

    private String username;

    private String password;

    private String email;

}
