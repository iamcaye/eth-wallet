package com.iamcaye.user_service.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.iamcaye.user_service.models.User;

@Repository
public interface UserRepository extends ListCrudRepository<User,String> {
    @Query("SELECT * FROM users WHERE email = :identificator or username = :identificator")
    public User getUserByIdentificator (String identificator);
}
