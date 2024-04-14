package com.eacuamba.dev.contacts_manager_backend.stories.contacts.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController

@RequiredArgsConstructor
@RequestMapping(path = "contacts")
public class DeleteContactController {

    private final DeleteContactService deleteContactService;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id")
            Long id
    ) {
        return this.deleteContactService.delete(id);
    }
}
