package com.iamcaye.user_service.models;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.iamcaye.user_service.dto.UserCreateDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table("users")
public class User {
    @Id
    Integer id;
    UUID uid;
    String username;
    String full_name;
    String email;
    Date dob;
    @Column("role_id")
    Integer role;

    public User (UserCreateDto user) {
        this(null, UUID.randomUUID(), user.username(), user.full_name(), user.email(), user.dob(), user.role_id().ordinal());
    }
}
