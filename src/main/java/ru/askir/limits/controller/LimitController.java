package ru.askir.limits.controller;

import org.springframework.http.HttpStatus;
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

    @PostMapping("/change")
    @ResponseStatus(HttpStatus.OK)
    LimitResponse changeLimit(@RequestBody LimitRequest limitRequest) {

        LimitResponse limitResponse = limitService.changeLimit(limitRequest);
        return limitResponse;
    }
}
