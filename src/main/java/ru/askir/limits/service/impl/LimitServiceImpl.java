package ru.askir.limits.service.impl;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import ru.askir.limits.config.AppConfig;
import ru.askir.limits.dto.LimitRequest;
import ru.askir.limits.dto.LimitResponse;
import ru.askir.limits.entity.Limit;
import ru.askir.limits.exception.LimitException;
import ru.askir.limits.repository.LimitRepository;
import ru.askir.limits.service.LimitService;

import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;
    private final AppConfig appConfig;

    public LimitServiceImpl(LimitRepository limitRepository, AppConfig appConfig) {
        this.limitRepository = limitRepository;
        this.appConfig = appConfig;
    }

    private void isExistsLimitByUserId(Long userId){
        limitRepository.findByUserId(userId).orElseThrow(
                () -> new LimitException(String.format("Не найден лимит для пользователя %s", userId)));
    }

    private Limit getLimitByUserId(Long userId){
        return limitRepository.findByUserId(userId).orElseThrow(
                () -> new LimitException(String.format("Не найден лимит для пользователя %s", userId)));
    }

    @Override
    @Transactional
    public LimitResponse decreaseLimit(LimitRequest limitRequest) {
        try {
            Limit limit = limitRepository
                    .findByUserId(limitRequest.userId())
                    .orElseGet(() ->
                            {
                                Limit newLimit = new Limit(limitRequest.userId(), appConfig.getPropertyConfig().getSumLimit());
                                limitRepository.save(newLimit);
                                return newLimit;
                            }
                    );

            if (limit.getSumLimit().subtract(limitRequest.amount()).compareTo(BigDecimal.valueOf(0)) < 0)
                throw new LimitException(String.format("Лимит %s меньше чем сумма платежа %s", limit.getSumLimit(), limitRequest.amount()));

            limitRepository.decreaseLimit(limitRequest.userId(), limitRequest.amount());

            limit = getLimitByUserId(limitRequest.userId());

            if (limit.getSumLimit().compareTo(BigDecimal.valueOf(0)) < 0)
                throw new LimitException(String.format("Лимит меньше суммы платежа %s", limitRequest.amount()));

            return new LimitResponse(limit.getSumLimit());
        }
        catch(Exception ex) {
            throw new LimitException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public LimitResponse increaseLimit(LimitRequest limitRequest) {
        isExistsLimitByUserId(limitRequest.userId());

        limitRepository.increaseLimit(limitRequest.userId(), limitRequest.amount());
        Limit limit = getLimitByUserId(limitRequest.userId());

        return new LimitResponse(limit.getSumLimit());
    }

    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public void initLimits() {
        limitRepository.initLimits(appConfig.getPropertyConfig().getSumLimit());
    }
}
