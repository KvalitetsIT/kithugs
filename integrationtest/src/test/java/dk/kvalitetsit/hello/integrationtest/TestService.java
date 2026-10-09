package dk.kvalitetsit.hello.integrationtest;

public sealed interface TestService permits OutsideDockerTestService, InDockerTestService {
    void start();

    void stop();

    String getHost();

    Integer getPort();

}