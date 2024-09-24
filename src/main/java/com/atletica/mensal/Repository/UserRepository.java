package com.atletica.mensal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atletica.mensal.Entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}