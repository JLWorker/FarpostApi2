package org.farpost.farpostapi2.services.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtClaim {

    USERNAME("username"),
    ROLE("role");

    private final String name;

}
