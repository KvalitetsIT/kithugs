package dk.kvalitetsit.hello.integrationtest;

import dk.kvalitetsit.hello.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.Collections;

public class ServiceStarter {
    private static final Logger logger = LoggerFactory.getLogger(ServiceStarter.class);
    private static final Logger serviceLogger = LoggerFactory.getLogger("kithugs");
    private static final Logger mariadbLogger = LoggerFactory.getLogger("mariadb");

    private static Network dockerNetwork;
    private static String jdbcUrl;
    private static boolean firstStart = true;

    public void startServices() {
        dockerNetwork = Network.newNetwork();

        setupDatabaseContainer();

        System.setProperty("JDBC.URL", jdbcUrl);
        System.setProperty("JDBC.USER", "hellouser");
        System.setProperty("JDBC.PASS", "secret1234");

        SpringApplication.run(Application.class);
    }

    public GenericContainer<?> startServicesInDocker() {
        if(firstStart) {
            firstStart = false;

            dockerNetwork = Network.newNetwork();

            setupDatabaseContainer();
        }

        GenericContainer<?> service;

        // Start service
        service = new GenericContainer<>("local/kithugs-qa:dev")
                .withFileSystemBind("/tmp", "/jacoco-output")
                .withEnv("JVM_OPTS", "-javaagent:/jacoco/jacocoagent.jar=output=file,destfile=/jacoco-output/jacoco-it.exec,dumponexit=true -cp integrationtest.jar");

        service.withNetwork(dockerNetwork)
                .withNetworkAliases("kithugs")

                .withEnv("LOG_LEVEL", "INFO")

                .withEnv("JDBC_URL", "jdbc:mariadb://mariadb:3306/hellodb")
                .withEnv("JDBC_USER", "hellouser")
                .withEnv("JDBC_PASS", "secret1234")

                .withEnv("spring.flyway.locations", "classpath:db/migration,filesystem:/app/sql")
                .withClasspathResourceMapping("db/migration/V901__extra_data_for_integration_test.sql", "/app/sql/V901__extra_data_for_integration_test.sql", BindMode.READ_ONLY)
//                .withEnv("JVM_OPTS", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000")

                .withExposedPorts(8081,8080)
                .waitingFor(Wait.forHttp("/actuator").forPort(8081).forStatusCode(200));
        service.start();
        attachLogger(serviceLogger, service);

        return service;
    }

    private boolean containerRunning(String containerName) {
        return DockerClientFactory
                .instance()
                .client()
                .listContainersCmd()
                .withNameFilter(Collections.singleton(containerName))
                .exec()
                .size() != 0;
    }

    private void setupDatabaseContainer() {
        // Database server.
        var mariadb = new MariaDBContainer<>("mariadb:10.6")
                .withDatabaseName("hellodb")
                .withUsername("hellouser")
                .withPassword("secret1234")
                .withNetwork(dockerNetwork)
                .withNetworkAliases("mariadb");
        mariadb.start();
        jdbcUrl = mariadb.getJdbcUrl();
        attachLogger(mariadbLogger, mariadb);
    }

    private void attachLogger(Logger logger, GenericContainer<?> container) {
        ServiceStarter.logger.info("Attaching logger to container: " + container.getContainerInfo().getName());
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(logger);
        container.followOutput(logConsumer);
    }
}
