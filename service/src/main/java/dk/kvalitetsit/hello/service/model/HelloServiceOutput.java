package dk.kvalitetsit.hello.service.model;

import java.time.ZonedDateTime;

public record HelloServiceOutput(String name, ZonedDateTime now) {
}
