package com.example.withauthmanager.dataBase.repositories;

import com.example.withauthmanager.dataBase.models.AuthorityEntity;
import com.example.withauthmanager.dataBase.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);
}
