package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.farpost.farpostapi2.enitities.Ad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class BotAdFileDto {

    @JsonProperty("_id")
    private Integer farpostId;
    private Integer position;
    private Integer limit;
    private Integer step;
    private List<String> url;
    private Integer falseAd;
    private Boolean isCashback;
    @JsonProperty("_is_on_hold_position")
    private Boolean isOnHoldPosition;
    private Integer overPrice;
    private Integer decisionMakingTime;
    private Integer minitOfNotPosition;
    private Integer timeDown;
    private Integer limitState;
    private Integer minPrice;
    private Boolean isGrafana;
    private Integer requestSleep;
    private List<Integer> weekdayActive;
    private List<List<Integer>> activity_time;
    private Boolean isMain;
    private String boobs;

    public BotAdFileDto(BotAdDto botAdDto, String boobs){
        this.farpostId = botAdDto.getFarpostId();
        this.position = botAdDto.getPosition();
        this.limit = botAdDto.getLimit();
        this.step = botAdDto.getStep();
        this.activity_time = List.of(List.of(botAdDto.getActivityTimeStartHour(), botAdDto.getActivityTimeStartMinit()),
                List.of(botAdDto.getActivityTimeStopHour(), botAdDto.getActivityTimeStopMinit()));
        this.url = botAdDto.getUrls();
        this.falseAd = botAdDto.getFalseAd();
        this.isCashback = botAdDto.getIsCashback();
        this.isOnHoldPosition = botAdDto.getIsOnHoldPosition();
        this.overPrice = botAdDto.getOverPrice();
        this.decisionMakingTime = botAdDto.getDecisionMakingTime();
        this.minitOfNotPosition = botAdDto.getMinitOfNotPosition();
        this.timeDown = botAdDto.getTimeDown();
        this.limitState = botAdDto.getLimitState();
        this.minPrice = botAdDto.getMinPrice();
        this.isGrafana = botAdDto.getIsGrafana();
        this.requestSleep = botAdDto.getRequestSleep();
        this.weekdayActive = botAdDto.getWeekdayActive();
        this.isMain = botAdDto.getIsMain();
        this.boobs = boobs;
    }

    public BotAdFileDto(Ad ad){

        List<String> urls = Arrays.stream(ad.getUrls().split(",")).map(String::trim).toList();
        List<Integer> weekdayActive = Arrays.stream(ad.getWeekdayActive().split(",")).map(el -> Integer.parseInt(el.trim())).toList();
        this.farpostId = ad.getFarpostId();
        this.position = ad.getPosition();
        this.limit = ad.getLimit();
        this.step = ad.getStep();
        this.activity_time = List.of(List.of(ad.getActivityTimeStartHour(), ad.getActivityTimeStartMinit()),
                List.of(ad.getActivityTimeStopHour(), ad.getActivityTimeStopMinit()));
        this.url = urls;
        this.falseAd = ad.getFalseAd();
        this.isCashback = ad.getIsCashback();
        this.isOnHoldPosition = ad.getIsOnHoldPosition();
        this.overPrice = ad.getOverPrice();
        this.decisionMakingTime = ad.getDecisionMakingTime();
        this.minitOfNotPosition = ad.getMinitOfNotPosition();
        this.timeDown = ad.getTimeDown();
        this.limitState = ad.getLimitState();
        this.minPrice = ad.getMinPrice();
        this.isGrafana = ad.getIsGrafana();
        this.requestSleep = ad.getRequestSleep();
        this.weekdayActive = weekdayActive;
        this.isMain = ad.getIsMain();
        this.boobs = ad.getClient().getBoobs();
    }

    public BotAdFileDto(Integer position, Integer limit, Integer step, List<String> url, Integer falseAd, Boolean isCashback, Boolean isOnHoldPosition, Integer overPrice, Integer decisionMakingTime, Integer minitOfNotPosition, Integer timeDown, Integer limitState, Integer minPrice, Boolean isGrafana, Integer requestSleep, List<Integer> weekdayActive, List<List<Integer>> activity_time, Boolean isMain, Integer botId, Integer clientId, Integer farpostId, String boobs) {
        this.farpostId =
        this.position = position;
        this.limit = limit;
        this.step = step;
        this.url = url;
        this.falseAd = falseAd;
        this.isCashback = isCashback;
        this.isOnHoldPosition = isOnHoldPosition;
        this.overPrice = overPrice;
        this.decisionMakingTime = decisionMakingTime;
        this.minitOfNotPosition = minitOfNotPosition;
        this.timeDown = timeDown;
        this.limitState = limitState;
        this.minPrice = minPrice;
        this.isGrafana = isGrafana;
        this.requestSleep = requestSleep;
        this.weekdayActive = weekdayActive;
        this.activity_time = activity_time;
        this.isMain = isMain;
        this.boobs = boobs;
    }

}
