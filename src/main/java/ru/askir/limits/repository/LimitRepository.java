package ru.askir.limits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.limits.entity.Limit;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Limit findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update Limit set sumLimit = (select sumLimit from Setup)")
    void initLimits();
}
