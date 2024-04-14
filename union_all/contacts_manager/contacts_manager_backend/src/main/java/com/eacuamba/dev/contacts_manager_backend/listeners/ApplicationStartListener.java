package com.eacuamba.dev.contacts_manager_backend.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStartListener {

    private final Environment environment;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) throws UnknownHostException {
        String port = environment.getProperty("local.server.port", "8080");;
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("Application running at {}", "http://" + hostAddress + ":" + port);
    }
}
