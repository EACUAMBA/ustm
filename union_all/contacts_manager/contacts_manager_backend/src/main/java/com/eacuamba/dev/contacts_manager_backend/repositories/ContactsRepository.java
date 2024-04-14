package com.eacuamba.dev.contacts_manager_backend.repositories;

import com.eacuamba.dev.contacts_manager_backend.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactEntity, Long> {
}
