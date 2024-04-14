package com.eacuamba.dev.contacts_manager_backend.stories.contacts.delete;

import com.eacuamba.dev.contacts_manager_backend.entities.ContactEntity;
import com.eacuamba.dev.contacts_manager_backend.repositories.ContactsRepository;
import com.eacuamba.dev.contacts_manager_backend.stories.CreateOrUpdateResponse;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteContactService {
    private final ContactsRepository contactsRepository;

    public ResponseEntity<Void> delete(Long id) {
        ContactEntity contact = this.contactsRepository.findById(id).orElseThrow();
        this.contactsRepository.delete(contact);

        return ResponseEntity.ok().build();
    }
}
