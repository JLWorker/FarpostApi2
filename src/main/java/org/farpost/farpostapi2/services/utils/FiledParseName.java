package org.farpost.farpostapi2.services.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FiledParseName {

    SERVER_OS("servers_os"),
    SERVER_PRESETS("server_presets"),
    LOCATIONS("locations");

    private final String value;


}
