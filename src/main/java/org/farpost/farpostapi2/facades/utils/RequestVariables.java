package org.farpost.farpostapi2.facades.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestVariables {

    AD_ID("ad_id"),
    AD_VIEW_DIR("view_dir"),
    AD_PRICE("price");

    private final String name;

}
