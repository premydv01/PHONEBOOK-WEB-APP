package com.pky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pky.entity.ContactEntity;

@Repository
public interface ContactDtlsRepository extends JpaRepository<ContactEntity,Integer>{

}
