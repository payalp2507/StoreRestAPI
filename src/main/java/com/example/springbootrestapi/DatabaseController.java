package com.example.springbootrestapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class DatabaseController {

    private final DataSource dataSource;

    @Autowired
    public DatabaseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/checkDatabase")
    public String checkDatabaseConnection() {
        try {
            dataSource.getConnection(); // Attempt to get a connection
            return "Database connection successful!";
        } catch (Exception e) {
            return "Failed to connect to the database: " + e.getMessage();
        }
    }
}
