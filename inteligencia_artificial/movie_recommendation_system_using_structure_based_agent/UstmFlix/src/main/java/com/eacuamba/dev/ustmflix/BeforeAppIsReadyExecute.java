package com.eacuamba.dev.ustmflix;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class BeforeAppIsReadyExecute implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        App.BuildGraph();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}