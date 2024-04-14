package com.eacuamba.dev.contacts_manager_backend.stories.contacts.update;

import com.eacuamba.dev.contacts_manager_backend.stories.CreateOrUpdateResponse;
import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "contacts")
public class UpdateContactController {

    private final UpdateContactService updateContactService;

    @PutMapping("{id}")
    public ResponseEntity<CreateOrUpdateResponse> update(
            @PathVariable("id")
            Long id,

            @RequestBody
            ContactRequest contactRequest
    ) {
        return this.updateContactService.update(id, contactRequest);
    }
}
