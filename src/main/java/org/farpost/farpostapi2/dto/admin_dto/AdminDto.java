package org.farpost.farpostapi2.dto.admin_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class AdminDto {

    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9_]{3,50}$", message = "Invalid username")
    @NotNull(message = "Username cannot be bull")
    @Schema(example = "Tolik")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).[a-zA-Z0-9&%$#]{8,20}$",
            message = "Password invalid")
    @NotNull(message = "Password cannot be bull")
    @Schema(example = "erdasd4535kld")
    private String password;

}
