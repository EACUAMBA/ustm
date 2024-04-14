package com.eacuamba.dev.contacts_manager_backend.stories.contacts.create;

import com.eacuamba.dev.contacts_manager_backend.stories.CreateOrUpdateResponse;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController

@RequiredArgsConstructor
@RequestMapping(path = "contacts")
public class CreateContactController {

    private final CreateContactService createContactService;

    @PostMapping
    public ResponseEntity<CreateOrUpdateResponse> create(@RequestBody ContactRequest contactRequest) {
        return this.createContactService.create(contactRequest);
    }
}
