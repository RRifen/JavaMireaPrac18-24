package com.example.javamireaprac18.jmx;

import com.example.javamireaprac18.services.SchedularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "Application config bean")
public class SchedulerMBean {
    private final SchedularService schedularService;

    @Autowired
    public SchedulerMBean(SchedularService schedularService) {
        this.schedularService = schedularService;
    }

    @ManagedOperation(description = "Recreate files in 'databaseFiles' directory")
    public void reCreateFiles() {
        schedularService.reCreateFiles();
    }

}
