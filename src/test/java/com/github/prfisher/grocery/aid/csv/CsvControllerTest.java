package com.github.prfisher.grocery.aid.csv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CsvControllerTest {
    private CsvController controller;

    @Before
    public void setup() throws Exception {
        this.controller = new CsvController();
    }

    @Test
    public void testControllerLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
