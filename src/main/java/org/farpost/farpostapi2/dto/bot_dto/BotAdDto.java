package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
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

    public interface NewAd {}

    public interface UpdateAd extends NewAd {}

    @NotNull(message = "Position cannot be null")
    @JsonView(value = {NewAd.class, UpdateAd.class})
    private Integer position;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Farpost id cannot be null")
    private Integer farpostId;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Limit cannot be null")
    private Integer limit;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Step cannot be null")
    private Integer step;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Time start hour cannot be null")
    private Integer activityTimeStartHour;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Time start minit cannot be null")
    private Integer activityTimeStartMinit;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Time stop hour cannot be null")
    private Integer activityTimeStopHour;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Time stop minit cannot be null")
    private Integer activityTimeStopMinit;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Urls cannot be null")
    @NotEmpty(message = "Urls cannot be empty")
    @Schema(example = "https://www.farpost.ru/vladivostok/service/medicine/+/Терапевтическая стоматология/")
    private List<String> urls;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "False ad cannot be null")
    private Integer falseAd;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Cashback cannot be null")
    private Boolean isCashback;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Hold position cannot be null")
    private Boolean isOnHoldPosition;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Over price cannot be null")
    private Integer overPrice;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Decision making time cannot be null")
    private Integer decisionMakingTime;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Minit of not position cannot be null")
    private Integer minitOfNotPosition;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Time down cannot be null")
    private Integer timeDown;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Limit state cannot be null")
    private Integer limitState;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Min price cannot be null")
    private Integer minPrice;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Grafana cannot be null")
    private Boolean isGrafana;

    @JsonView(value = {NewAd.class, UpdateAd.class})
    @NotNull(message = "Request sleep cannot be null")
    private Integer requestSleep;

    @NotNull(message = "Weekday active cannot be null")
    @NotEmpty(message = "Weekday active cannot be empty")
    @JsonView(value = {NewAd.class, UpdateAd.class})
    private List<Integer> weekdayActive;

    @NotNull(message = "Is main cannot be null")
    @JsonView(value = {NewAd.class, UpdateAd.class})
    private Boolean isMain;

    @JsonView(value = NewAd.class)
    @NotNull(message = "Bot id cannot be null")
    private Integer botId;

    @JsonView(value = NewAd.class)
    @NotNull(message = "Client id cannot be null")
    private Integer clientId;

}
