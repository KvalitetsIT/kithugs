package dk.kvalitetsit.hello.integrationtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;

import java.io.IOException;
import java.net.URISyntaxException;

abstract class AbstractIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(AbstractIntegrationTest.class);

    public static GenericContainer helloService;
    private static String apiBasePath;

    @AfterAll
    static void afterClass() {
        if(helloService != null) {
            helloService.getDockerClient().stopContainerCmd(helloService.getContainerId()).exec();
        }
    }

    @BeforeAll
    static void beforeClass() throws IOException, URISyntaxException {
        setup();
    }

    private static void setup() throws IOException, URISyntaxException {
        var runInDocker = Boolean.getBoolean("runInDocker");
        logger.info("Running integration test in docker container: " + runInDocker);

        ServiceStarter serviceStarter;
        serviceStarter = new ServiceStarter();
        if(runInDocker) {
            helloService = serviceStarter.startServicesInDocker();
            apiBasePath = "http://" + helloService.getContainerIpAddress() + ":" + helloService.getMappedPort(8080);
        }
        else {
            serviceStarter.startServices();
            apiBasePath = "http://localhost:8080";
        }
    }

    String getApiBasePath() {
        return apiBasePath;
    }
}
