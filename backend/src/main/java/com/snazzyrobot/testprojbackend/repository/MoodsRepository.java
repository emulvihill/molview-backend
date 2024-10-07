package com.snazzyrobot.testprojbackend.repository;

import com.snazzyrobot.testprojbackend.entity.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodsRepository extends JpaRepository<Mood, Long> {
}