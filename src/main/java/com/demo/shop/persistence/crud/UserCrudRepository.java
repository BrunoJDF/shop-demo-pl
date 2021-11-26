package com.demo.shop.persistence.crud;

import com.demo.shop.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String username);
}
