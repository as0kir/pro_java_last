package ru.askir.limits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.askir.limits.entity.Limit;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query("update Limit set sumLimit = :sumLimit")
    void initLimits(BigDecimal sumLimit);

    @Modifying(clearAutomatically = true)
    @Query("update Limit set sumLimit = sumLimit - :sumLimit where userId = :userId")
    void decreaseLimit(Long userId, BigDecimal sumLimit);

    @Modifying(clearAutomatically = true)
    @Query("update Limit set sumLimit = sumLimit + :sumLimit where userId = :userId")
    void increaseLimit(Long userId, BigDecimal sumLimit);
}
