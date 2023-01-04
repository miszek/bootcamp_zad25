package com.michalszekalski.bootcamp_zad25;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIsDoneOrderByDeadlineAsc(Boolean isDone);

    @Query("SELECT t FROM Task t ORDER BY t.deadline ASC")
    List<Task> findAllOrderByDeadlineAsc();
}
