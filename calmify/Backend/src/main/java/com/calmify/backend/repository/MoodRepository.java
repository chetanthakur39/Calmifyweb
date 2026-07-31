package com.calmify.backend.repository;

import com.calmify.backend.entity.MoodEntry;
import com.calmify.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodRepository extends JpaRepository<MoodEntry, Long> {

    List<MoodEntry> findByUser(User user);
    MoodEntry findTopByUserOrderByCreatedAtDesc(User user);
}