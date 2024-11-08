package ru.askir.limits.service;

import ru.askir.limits.dto.LimitRequest;
import ru.askir.limits.dto.LimitResponse;

public interface LimitService {
    LimitResponse changeLimit(LimitRequest limitRequest);

    void initLimits();
}
