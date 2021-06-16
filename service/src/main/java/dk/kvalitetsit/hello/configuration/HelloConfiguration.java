package dk.kvalitetsit.hello.configuration;

import dk.kvalitetsit.hello.service.HelloService;
import dk.kvalitetsit.hello.service.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration{
    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }
}
