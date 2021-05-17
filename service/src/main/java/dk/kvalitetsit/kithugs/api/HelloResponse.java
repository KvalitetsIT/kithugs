package dk.kvalitetsit.kithugs.api;

import java.time.ZonedDateTime;

public class HelloResponse {
    private ZonedDateTime now;
    private String name;

    public ZonedDateTime getNow() {
        return now;
    }

    public void setNow(ZonedDateTime now) {
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
