package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class BotAdFileVersionDto {

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

    public BotAdFileVersionDto(BotAdDto botAdDto, String boobs){
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

    public BotAdFileVersionDto(Integer position, Integer limit, Integer step, List<String> url, Integer falseAd, Boolean isCashback, Boolean isOnHoldPosition, Integer overPrice, Integer decisionMakingTime, Integer minitOfNotPosition, Integer timeDown, Integer limitState, Integer minPrice, Boolean isGrafana, Integer requestSleep, List<Integer> weekdayActive, List<List<Integer>> activity_time, Boolean isMain, Integer botId, Integer clientId, Integer farpostId, String boobs) {
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

    public BotAdFileVersionDto(String boobs){
        this.boobs = boobs;
        this.position = 0;
        this.limit = 0;
        this.step = 0;
        this.farpostId = 0;
        this.url = List.of("asd");
        this.falseAd = 0;
        this.isCashback = false;
        this.isOnHoldPosition = false;
        this.overPrice = 0;
        this.decisionMakingTime = 0;
        this.minitOfNotPosition = 0;
        this.timeDown = 0;
        this.limitState = 0;
        this.minPrice = 0;
        this.isGrafana = false;
        this.requestSleep = 0;
        this.weekdayActive = List.of(0);
        this.isMain = false;
        this.activity_time = List.of(List.of(1,2), List.of(3,4));
    }

}
