package org.nagarro.product.catalouge.repository;

import org.nagarro.product.catalouge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUsername(String username);
}
