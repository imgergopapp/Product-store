package com.codecool;

import com.codecool.web.service.simple.TravicCiTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTest {
    @Test
    @DisplayName("Simple test1")
    void test1() {
        assertEquals(1, TravicCiTestService.test());
    }
}


