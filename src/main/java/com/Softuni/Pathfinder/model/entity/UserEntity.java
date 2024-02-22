package com.Softuni.Pathfinder.model.entity;

import com.Softuni.Pathfinder.model.entity.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;
    @Column
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private Integer age;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;
    @ManyToMany
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
}
