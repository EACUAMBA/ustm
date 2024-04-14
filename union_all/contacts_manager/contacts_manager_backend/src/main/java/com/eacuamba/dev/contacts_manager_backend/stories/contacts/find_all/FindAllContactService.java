package com.eacuamba.dev.contacts_manager_backend.stories.contacts.find_all;

import com.eacuamba.dev.contacts_manager_backend.entities.ContactEntity;
import com.eacuamba.dev.contacts_manager_backend.repositories.ContactsRepository;
import com.eacuamba.dev.contacts_manager_backend.stories.CreateOrUpdateResponse;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactRequest;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllContactService {
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

    public ResponseEntity<List<ContactResponse>> findAll() {
        List<ContactResponse> contactResponseList = this.contactsRepository.findAll()
                .stream().map(
                        contact -> ContactResponse
                                .builder()
                                .id(contact.getId())
                                .name(contact.getName())
                                .number(contact.getNumber())
                                .email(contact.getEmail())
                                .build()
                )
                .toList();

        return ResponseEntity.ok(contactResponseList);
    }
}
