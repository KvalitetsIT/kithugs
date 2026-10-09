package dk.kvalitetsit.hello.integrationtest.environment;

import org.slf4j.Logger;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.io.File;
import java.time.Duration;

final class InDockerTestService implements TestService {
    private final String serviceName;
    private final WaitStrategy waitStrategy;
    private final File composeFile;
    private final Slf4jLogConsumer logConsumer;
    private ComposeContainer container;

    public InDockerTestService(File composeFile, String serviceName, Logger logger) {
        this.serviceName = serviceName;
        logConsumer = new Slf4jLogConsumer(logger).withPrefix(serviceName);
        this.composeFile = composeFile;
        waitStrategy = Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(60));
    }

    @Override
    public void start() {
        container = createComposeContainer();
        container.start();
    }

    private ComposeContainer createComposeContainer() {
        return new ComposeContainer(composeFile)
                .withServices(serviceName)
                .withExposedService(serviceName, 8080, waitStrategy)
                .withLogConsumer(serviceName, logConsumer);
    }

    @Override
    public void stop() {
        container.stop();
    }

    @Override
    public String getHost() {
        return container.getServiceHost(serviceName, 8080);
    }

    @Override
    public Integer getPort() {
        return container.getServicePort(serviceName, 8080);
    }
}