package com.bank.cc.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.cc.dao.model.CreditCardTransactionEntity;

@Repository
@Transactional
public interface CreditCardTransactionRepository extends CrudRepository<CreditCardTransactionEntity, Long> {

}
