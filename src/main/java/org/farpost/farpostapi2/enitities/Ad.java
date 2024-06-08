package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.bot_dto.BotAdDto;

@Entity
@Getter
@Setter
@Table(name = "ads")
@NoArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer position;
    private Integer farpostId;
    @Column(name = "limit_pr")
    private Integer limit;
    private Integer step;
    private Integer activityTimeStartHour;
    private Integer activityTimeStartMinit;
    private Integer activityTimeStopHour;
    private Integer activityTimeStopMinit;
    private String urls;
    private Integer falseAd;
    private Boolean isCashback;
    private Boolean isOnHoldPosition;
    private Integer overPrice;
    private Integer decisionMakingTime;
    private Integer minitOfNotPosition;
    private Integer timeDown;
    private Integer limitState;
    private Integer minPrice;
    private Boolean isGrafana;
    private Integer requestSleep;
    private String weekdayActive;
    private Boolean isMain;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private Client client;

    @OneToOne()
    @JoinColumn(name = "bot_id")
    @JsonBackReference
    private Bot bot;

    public Ad(BotAdDto botAdDto, Client client, Bot bot){

        StringBuilder urlBuilder = new StringBuilder();
        StringBuilder weekActiveBuilder = new StringBuilder();
        botAdDto.getUrls().forEach(el -> urlBuilder.append(el).append(", "));
        botAdDto.getWeekdayActive().forEach(el -> weekActiveBuilder.append(el).append(", "));

        this.farpostId = botAdDto.getFarpostId();
        this.position = botAdDto.getPosition();
        this.limit = botAdDto.getLimit();
        this.step = botAdDto.getStep();
        this.activityTimeStartHour = botAdDto.getActivityTimeStartHour();
        this.activityTimeStartMinit = botAdDto.getActivityTimeStartMinit();
        this.activityTimeStopHour = botAdDto.getActivityTimeStopHour();
        this.activityTimeStopMinit = botAdDto.getActivityTimeStopMinit();
        this.urls = urlBuilder.substring(0, urlBuilder.length()-3);
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
        this.weekdayActive = weekActiveBuilder.substring(0, weekActiveBuilder.length()-3);
        this.isMain = botAdDto.getIsMain();
        this.client = client;
        this.bot = bot;
    }

}
