package com.iamcaye.user_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.iamcaye.user_service.models.User;

@Repository
public interface UserRepository extends ListCrudRepository<User,String> {
    
}
