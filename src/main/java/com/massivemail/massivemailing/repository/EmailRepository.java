package com.massivemail.massivemailing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.massivemail.massivemailing.entity.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long>  {

}
