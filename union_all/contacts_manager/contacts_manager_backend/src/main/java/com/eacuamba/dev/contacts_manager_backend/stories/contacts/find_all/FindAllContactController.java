package com.eacuamba.dev.contacts_manager_backend.stories.contacts.find_all;

import com.eacuamba.dev.contacts_manager_backend.stories.contacts.ContactResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController

@RequiredArgsConstructor
@RequestMapping(path = "contacts")
public class FindAllContactController {

    private final FindAllContactService findAllContactService;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> create() {
        return this.findAllContactService.findAll();
    }
}
