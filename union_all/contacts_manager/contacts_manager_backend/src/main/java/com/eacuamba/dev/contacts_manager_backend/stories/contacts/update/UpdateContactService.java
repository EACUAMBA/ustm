package com.eacuamba.dev.contacts_manager_backend.stories.contacts.update;

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
public class UpdateContactService {
    private final ContactsRepository contactsRepository;

    public ResponseEntity<CreateOrUpdateResponse> update(Long id, ContactRequest contactRequest) {
        ContactEntity contact = this.contactsRepository.findById(id).orElseThrow();
        contact = contact.toBuilder()
                .name(contactRequest.getName())
                .number(contactRequest.getNumber())
                .email(contactRequest.getEmail())
                .build();

        ContactEntity contactUpdated = this.contactsRepository.save(contact);

        return ResponseEntity.ok(CreateOrUpdateResponse.builder().id(contactUpdated.getId()).build());
    }
}
