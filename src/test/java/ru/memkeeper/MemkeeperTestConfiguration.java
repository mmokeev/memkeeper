package ru.memkeeper;

import org.springframework.context.annotation.Import;

@Import(MainConfiguration.class)
public class MemkeeperTestConfiguration {
    public final static String TEST_PROFILE = "test";
}
