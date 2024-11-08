package ru.askir.limits.controller;

import org.springframework.web.bind.annotation.*;
import ru.askir.limits.dto.LimitRequest;
import ru.askir.limits.dto.LimitResponse;
import ru.askir.limits.service.LimitService;

@RestController
@RequestMapping("/limit")
public class LimitController {
    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PostMapping("/decrease")
    LimitResponse decreaseLimit(@RequestBody LimitRequest limitRequest) {
        return limitService.decreaseLimit(limitRequest);
    }

    @PostMapping("/increase")
    LimitResponse inceaseLimit(@RequestBody LimitRequest limitRequest) {
        return limitService.increaseLimit(limitRequest);
    }
}
