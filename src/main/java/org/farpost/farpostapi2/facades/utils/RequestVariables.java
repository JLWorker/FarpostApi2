package org.farpost.farpostapi2.facades.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestVariables {

    AD_ID("ad_id"),
    AD_VIEW_DIR("view_dir"),
    AD_PRICE("price"),
    TIMEWEB_ID("timeweb_id"),
    BOT_NAME("bot_name");

    private final String name;

}
