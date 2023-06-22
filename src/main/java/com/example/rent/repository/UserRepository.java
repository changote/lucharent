package com.example.rent.repository;

import com.example.rent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(@Param("username") String username);

}
