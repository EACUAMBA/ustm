package com.eacuamba.dev.command_line_interface;

import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.model.Access;
import com.eacuamba.dev.domain.model.User;
import com.eacuamba.dev.domain.repository.UserRepository;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class AccessService {

    public static void addAccess(String resource){
        if(isNull(resource)){
            return;
        }
        addAccess(resource, ApplicationConfig.authenticatedUser);

    }

    public static void addAccess(String resource, User user){
        Access access = new Access();
        access.setDateTime(LocalDateTime.now());
        access.setResource(resource);
        access.setUser(user);
        user.addAccess(access);

        UserRepository.saveAccess(access);
    }
}
