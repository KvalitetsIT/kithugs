package dk.kvalitetsit.hello.integrationtest;

import org.junit.jupiter.api.AfterEach;
import org.openapitools.client.ApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.TimeZone;

public abstract class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    private static final String APP_SERVICE_NAME = "helloservice";
    private static final String DB_SERVICE_NAME = "mariadb";
    private static final String DB_NAME = "hellodb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    protected static final ComposeContainer dbEnvironment = new ComposeContainer(getComposeFile())
            .withServices(DB_SERVICE_NAME)
            .withExposedService(DB_SERVICE_NAME, 3306, Wait.forHealthcheck())
            .withLogConsumer(DB_SERVICE_NAME, new Slf4jLogConsumer(logger).withPrefix(DB_SERVICE_NAME));
    protected static Database appDatabase;

    protected static Component component;
    protected static ApiClient client;

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen")); // Same as timezone used in docker containers

        dbEnvironment.start();
        appDatabase = getDatabase();

        boolean runInDocker = Boolean.getBoolean("runInDocker");
        component = runInDocker ? new InDockerComponent(getComposeFile(), APP_SERVICE_NAME, logger) : new OutsideDockerComponent(getProperties());
        client = new ApiClient();
        startService();
    }

    @AfterEach
    void afterEach() {
        appDatabase.clear();
    }

    private static void startService() {
        component.start();
        client.setBasePath(String.format("http://%s:%s", component.getHost(), component.getPort()));
    }

    public static File getComposeFile() {
        var testWorkingDir = System.getProperty("user.dir");
        var projectRoot = Paths.get(testWorkingDir).toAbsolutePath().normalize().getParent().toFile();
        return new File(projectRoot, "compose/docker-compose.yaml");
    }

    private static Database getDatabase() {
        var host = dbEnvironment.getServiceHost(DB_SERVICE_NAME, 3306);
        var port = dbEnvironment.getServicePort(DB_SERVICE_NAME, 3306);
        return new Database(host, port, DB_NAME, DB_USER, DB_PASSWORD);
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        String host = dbEnvironment.getServiceHost(DB_SERVICE_NAME, 3306);
        Integer port = dbEnvironment.getServicePort(DB_SERVICE_NAME, 3306);
        properties.setProperty("JDBC.URL", "jdbc:mariadb://" + host + ":" + port + "/" + DB_NAME);
        properties.setProperty("JDBC.USER", DB_USER);
        properties.setProperty("JDBC.PASS", DB_PASSWORD);
        return properties;
    }

}
