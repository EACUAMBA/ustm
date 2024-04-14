package com.eacuamba.dev.contacts_manager_backend.stories.contacts.create;

import com.eacuamba.dev.contacts_manager_backend.entities.ContactEntity;
import com.eacuamba.dev.contacts_manager_backend.stories.CreateOrUpdateResponse;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactRequest;
import com.eacuamba.dev.contacts_manager_backend.repositories.ContactsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateContactService {
    private final ContactsRepository contactsRepository;

    public ResponseEntity<CreateOrUpdateResponse> create(ContactRequest contactRequest) {
        log.info("Request: " + contactRequest);

        ContactEntity contact = ContactEntity.builder()
                .name(contactRequest.getName())
                .number(contactRequest.getNumber())
                .email(contactRequest.getEmail())
                .build();

        ContactEntity contactSaved = this.contactsRepository.save(contact);


        return ResponseEntity.ok(CreateOrUpdateResponse.builder().id(contactSaved.getId()).build());
    }
}
