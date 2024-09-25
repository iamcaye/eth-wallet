package com.iamcaye.user_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users_credentials")
public record UserCredentials(
    @Id
    Integer id,
    @Column("user_id")
    Integer userId,
    Integer provider_id,
    String hash
) { }
