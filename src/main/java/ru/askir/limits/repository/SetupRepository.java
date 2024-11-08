package ru.askir.limits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.askir.limits.entity.Setup;

public interface SetupRepository extends JpaRepository<Setup, Long> {
    @Query("select s from Setup s")
    Setup getSetup();
}
