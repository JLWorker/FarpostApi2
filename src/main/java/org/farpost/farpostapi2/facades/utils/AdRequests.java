package org.farpost.farpostapi2.facades.utils;

public interface AdRequests {

    String UP_AD = "https://www.farpost.ru/bulletin/service-configure?applier=stickBulletin&auto_apply=1&ids={ad_id}&dir[{ad_id}]={view_dir}&stickPrice[{ad_id}]={price}";
    String UNPIN_AD = "https://www.farpost.ru/bulletin/service-configure?ids={ad_id}&applier=unStickBulletin&auto_apply=1";

}
