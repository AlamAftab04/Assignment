package com.bank.cc.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.cc.dao.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
