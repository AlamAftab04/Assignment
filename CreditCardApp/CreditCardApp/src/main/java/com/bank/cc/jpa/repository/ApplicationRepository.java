package com.bank.cc.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.cc.dao.model.ApplicationEntity;

@Repository
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long>{

}
