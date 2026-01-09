package com.labmanager.users.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerUnitTest {

    @Test
    void healthReturnsUp() {
        HealthController controller = new HealthController();
        ResponseEntity<Map<String, String>> resp = controller.health();

        assertNotNull(resp);
        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("UP", resp.getBody().get("status"));
    }

}
