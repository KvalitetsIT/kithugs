package dk.kvalitetsit.hello.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatasourceConfiguration(
        @NotBlank String url,
        @NotBlank String username,
        @NotNull String password
) {
}