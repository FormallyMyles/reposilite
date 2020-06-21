package org.panda_lang.reposilite.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationLoaderTest {

    @TempDir
    protected File workingDirectory;

    @Test
    void shouldLoadFileWithCustomProperties() {
        System.setProperty("reposilite.hostname", "localhost");               // String type
        System.setProperty("reposilite.port", "8080");                        // Integer type
        System.setProperty("reposilite.debugEnabled", "true");                // Boolean type
        System.setProperty("reposilite.repositories", "http://a.com,b.com");  // List<String> type

        Configuration configuration = ConfigurationLoader.load(workingDirectory.getAbsolutePath());
        assertEquals("localhost", configuration.getHostname());
        assertEquals(8080, configuration.getPort());
        assertTrue(configuration.isDebugEnabled());
        assertEquals(Arrays.asList("http://a.com", "b.com"), configuration.getRepositories());
    }

}