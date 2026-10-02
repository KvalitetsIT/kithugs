package dk.kvalitetsit.hello.integrationtest;

import dk.kvalitetsit.hello.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;


final class OutsideDockerComponent implements Component {
    private final Properties properties;
    private ConfigurableApplicationContext app;

    public OutsideDockerComponent(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void start() {
        System.getProperties().putAll(properties);
        app = SpringApplication.run(Application.class);
    }

    @Override
    public void stop() {
        if (app != null) {
            app.close();
        }
    }

    @Override
    public String getHost() {
        return "localhost";
    }

    @Override
    public Integer getPort() {
        return 8080;
    }
}

