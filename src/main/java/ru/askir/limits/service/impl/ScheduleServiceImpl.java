package ru.askir.limits.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.askir.limits.service.LimitService;
import ru.askir.limits.service.ScheduleService;

@Component
public class ScheduleServiceImpl implements ScheduleService {
    private final LimitService limitService;

    public ScheduleServiceImpl(LimitService limitService) {
        this.limitService = limitService;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void initLimits() {
        limitService.initLimits();
    }
}
