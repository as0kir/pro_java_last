package ru.askir.limits.service;

import ru.askir.limits.dto.LimitRequest;
import ru.askir.limits.dto.LimitResponse;

public interface LimitService {
    LimitResponse decreaseLimit(LimitRequest limitRequest);
    LimitResponse increaseLimit(LimitRequest limitRequest);
    void initLimits();
}
