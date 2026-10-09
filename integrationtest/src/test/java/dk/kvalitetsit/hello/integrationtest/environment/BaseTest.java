package dk.kvalitetsit.hello.integrationtest.environment;

import org.junit.jupiter.api.AfterEach;

/**
 * Base class for integration tests that provides a test environment and ensures cleanup after each test.
 */
public abstract class BaseTest {
    private final TestEnvironment environment = new TestEnvironment();

    @AfterEach
    void afterEach() {
        environment.getDatabase().clear();
    }

    protected TestEnvironment getEnvironment() {
        return environment;
    }

}
