package com.example.withauthmanager.dataBase.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "user_role")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "roleEntity")
    private Set<AuthorityEntity> authorityEntityList;

    private String roleValue;
}
