package org.farpost.farpostapi2.facades.utils;

public interface TimewebRequests {

    String GET_OS = "https://api.timeweb.cloud/api/v1/os/servers";
    String GET_RESETS = "https://api.timeweb.cloud/api/v1/presets/servers";
    String GET_LOCATIONS = "https://api.timeweb.cloud/api/v2/locations";
    String CREATE_VPS = "https://api.timeweb.cloud/api/v1/servers";
    String GET_IMAGES = "https://api.timeweb.cloud/api/v1/images";
    String GET_SERVER = "https://api.timeweb.cloud/api/v1/servers/{timeweb_id}";
    String ADD_IPV4 = "https://api.timeweb.cloud/api/v1/servers/{timeweb_id}/ips";
}
