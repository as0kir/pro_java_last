package ru.askir.limits.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.limits.repository.SetupRepository;
import ru.askir.limits.service.SetupService;

import java.math.BigDecimal;

@Service
public class SetupServiceImpl implements SetupService {
    private final SetupRepository setupRepository;

    public SetupServiceImpl(SetupRepository setupRepository) {
        this.setupRepository = setupRepository;
    }

    @Override
    public BigDecimal getSumLimit() {
        return setupRepository.getSetup().getSumLimit();
    }
}
