package dk.kvalitetsit.kithugs.configuration;

import dk.kvalitetsit.kithugs.service.HelloService;
import dk.kvalitetsit.kithugs.service.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration{
    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }
}
