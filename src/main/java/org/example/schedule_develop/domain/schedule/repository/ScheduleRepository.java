package org.example.schedule_develop.domain.schedule.repository;

import org.example.schedule_develop.common.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
