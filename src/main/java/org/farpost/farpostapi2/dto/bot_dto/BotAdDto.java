package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class BotAdDto {

    @NotNull(message = "Position cannot be null")
    private Integer position;
    
    @NotNull(message = "Farpost id cannot be null")
    private Integer farpostId;

    @NotNull(message = "Limit cannot be null")
    private Integer limit;

    @NotNull(message = "Step cannot be null")
    private Integer step;

    @NotNull(message = "Time start hour cannot be null")
    private Integer activityTimeStartHour;

    @NotNull(message = "Time start minit cannot be null")
    private Integer activityTimeStartMinit;

    @NotNull(message = "Time stop hour cannot be null")
    private Integer activityTimeStopHour;

    @NotNull(message = "Time stop minit cannot be null")
    private Integer activityTimeStopMinit;

    @NotNull(message = "Urls cannot be null")
    @NotEmpty(message = "Urls cannot be empty")
    private List<String> urls;

    @NotNull(message = "False ad cannot be null")
    private Integer falseAd;

    @NotNull(message = "Cashback cannot be null")
    private Boolean isCashback;

    @NotNull(message = "Hold position cannot be null")
    private Boolean isOnHoldPosition;

    @NotNull(message = "Over price cannot be null")
    private Integer overPrice;

    @NotNull(message = "Decision making time cannot be null")
    private Integer decisionMakingTime;

    @NotNull(message = "Minit of not position cannot be null")
    private Integer minitOfNotPosition;

    @NotNull(message = "Time down cannot be null")
    private Integer timeDown;

    @NotNull(message = "Limit state cannot be null")
    private Integer limitState;

    @NotNull(message = "Min price cannot be null")
    private Integer minPrice;

    @NotNull(message = "Grafana cannot be null")
    private Boolean isGrafana;

    @NotNull(message = "Request sleep cannot be null")
    private Integer requestSleep;

    @NotNull(message = "Weekday active cannot be null")
    @NotEmpty(message = "Weekday active cannot be empty")
    private List<Integer> weekdayActive;

    @NotNull(message = "Is main cannot be null")
    private Boolean isMain;

    @NotNull(message = "Bot id cannot be null")
    private Integer botId;

    @NotNull(message = "Client id cannot be null")
    private Integer clientId;

}