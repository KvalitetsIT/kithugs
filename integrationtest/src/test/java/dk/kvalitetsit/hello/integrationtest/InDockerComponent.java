package dk.kvalitetsit.hello.integrationtest;

import org.slf4j.Logger;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.io.File;
import java.time.Duration;

final class InDockerComponent implements Component {
    private final String serviceName;
    private final WaitStrategy waitStrategy;
    private final File composeFile;
    private final Slf4jLogConsumer logConsumer;
    private ComposeContainer component;

    public InDockerComponent(File composeFile, String serviceName, Logger logger) {
        this.serviceName = serviceName;
        logConsumer = new Slf4jLogConsumer(logger).withPrefix(serviceName);
        this.composeFile = composeFile;
        waitStrategy = Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(60));
    }

    @Override
    public void start() {
        component = createComposeContainer();
        component.start();
    }

    private ComposeContainer createComposeContainer() {
        return new ComposeContainer(composeFile)
                .withServices(serviceName)
                .withExposedService(serviceName, 8080, waitStrategy)
                .withLogConsumer(serviceName, logConsumer);
    }

    @Override
    public void stop() {
        component.stop();
    }

    @Override
    public String getHost() {
        return component.getServiceHost(serviceName, 8080);
    }

    @Override
    public Integer getPort() {
        return component.getServicePort(serviceName, 8080);
    }
}