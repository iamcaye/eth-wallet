package com.iamcaye.user_service.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.iamcaye.user_service.models.UserCredentials;

public interface UserCredentialsRepository extends ListCrudRepository<UserCredentials, Integer> {
    List<UserCredentials> findAllByUserId (Integer user_id);
}
