package org.example.schedule_develop.domain.schedule.repository;

import io.micrometer.common.lang.NonNull;
import org.example.schedule_develop.common.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAll(@NonNull Pageable pageable);

}
