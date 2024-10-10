package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    // weather와 기간이 모두 있을 경우
    @Query("SELECT t FROM Todo t WHERE t.weather = :weather AND t.modifiedAt BETWEEN :startDate AND :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findByWeatherAndDateRange(Pageable pageable, @Param("weather") String weather, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // weather만 있을 경우
    @Query("SELECT t FROM Todo t WHERE t.weather = :weather ORDER BY t.modifiedAt DESC")
    Page<Todo> findByWeather(Pageable pageable, @Param("weather") String weather);

    // 기간만 있을 경우
    @Query("SELECT t FROM Todo t WHERE t.modifiedAt BETWEEN :startDate AND :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findByDateRange(Pageable pageable, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}