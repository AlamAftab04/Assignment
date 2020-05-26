package com.bank.cc.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.cc.dao.model.ApplicationEntity;

@Repository
public interface ApplicationRepo extends CrudRepository<ApplicationEntity, Long>{

}
