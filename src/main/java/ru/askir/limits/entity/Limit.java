package ru.askir.limits.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "limits")
@Getter
@Setter
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private BigDecimal sumLimit;

    public Limit() {
    }

    public Limit(Long userId, BigDecimal sumLimit) {
        this.userId = userId;
        this.sumLimit = sumLimit;
    }
}
