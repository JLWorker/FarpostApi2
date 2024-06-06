package org.farpost.farpostapi2.dto.timeweb_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimewebVpsResponseDto {

    private Integer vpsTimewebId;
    private String rootPassword;
    private String ipv6;
    private String ipv4;

    public TimewebVpsResponseDto(Integer vpsTimewebId, String rootPassword, String ipv6, String ipv4) {
        this.vpsTimewebId = vpsTimewebId;
        this.rootPassword = rootPassword;
        this.ipv6 = ipv6;
        this.ipv4 = ipv4;
    }
}
