package ru.askir.limits.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.limits.dto.LimitRequest;
import ru.askir.limits.dto.LimitResponse;
import ru.askir.limits.entity.Limit;
import ru.askir.limits.exception.LimitException;
import ru.askir.limits.repository.LimitRepository;
import ru.askir.limits.service.LimitService;
import ru.askir.limits.service.SetupService;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;
    private final SetupService setupService;
    private final ReentrantLock lock;

    public LimitServiceImpl(LimitRepository limitRepository, SetupService setupService) {
        this.limitRepository = limitRepository;
        this.setupService = setupService;
        lock = new ReentrantLock();
    }

    @Override
    public LimitResponse changeLimit(LimitRequest limitRequest) {
        try {
            lock.lock();

            Limit limit = limitRepository.findByUserId(limitRequest.userId());
            if(limit == null) {
                limit = new Limit();
                limit.setUserId(limitRequest.userId());
                limit.setSumLimit(setupService.getSumLimit());
            }

            if(limit.getSumLimit().subtract(limitRequest.amount()).compareTo(BigDecimal.valueOf(0)) < 0)
                throw new LimitException(String.format("Лимит %s меньше чем сумма платежа %s", limit.getSumLimit(), limitRequest.amount()));

            limit.setSumLimit(limit.getSumLimit().subtract(limitRequest.amount()));
            limitRepository.save(limit);

            return new LimitResponse(limit.getSumLimit());
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void initLimits() {
        try {
            lock.lock();
            limitRepository.initLimits();
        }
        finally {
            lock.unlock();
        }
    }
}
