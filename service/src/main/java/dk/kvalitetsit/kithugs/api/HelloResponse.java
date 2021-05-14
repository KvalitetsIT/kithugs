package dk.kvalitetsit.kithugs.api;

import java.time.LocalDateTime;

public class HelloResponse {
    private LocalDateTime now;
    private String name;

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
