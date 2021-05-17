package dk.kvalitetsit.kithugs.service.model;

import java.time.ZonedDateTime;

public class HelloServiceOutput {
    private String name;
    private ZonedDateTime now;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getNow() {
        return now;
    }

    public void setNow(ZonedDateTime now) {
        this.now = now;
    }
}
