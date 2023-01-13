package com.example.withauthmanager.dataBase.repositories;

import com.example.withauthmanager.dataBase.models.AuthorityEntity;
import com.example.withauthmanager.dataBase.models.RoleEntity;
import com.example.withauthmanager.dataBase.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    long ID_OF_DEFAULT_ROLE = 1;

    @Override
    Optional<RoleEntity> findById(Long aLong);
}
