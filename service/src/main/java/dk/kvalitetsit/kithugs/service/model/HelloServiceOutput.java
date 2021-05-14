package dk.kvalitetsit.kithugs.service.model;

import java.time.LocalDateTime;

public class HelloServiceOutput {
    private String name;
    private LocalDateTime now;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
}
