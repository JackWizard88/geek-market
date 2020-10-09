package com.geekbrains.spring.market.geekmarket.repositories;



import com.geekbrains.spring.market.geekmarket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
