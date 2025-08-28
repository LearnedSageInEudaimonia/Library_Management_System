package com.aat.Library_Management_System;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class LibraryManagementSystemApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
        // It will fail if there are any configuration issues or missing beans
    }

    @Test
    void applicationStarts() {
        // This test verifies that the application can start without errors
        // It's a basic smoke test for the entire application
    }
}
