package com.snazzyrobot.molviewbackend.repository;

import com.snazzyrobot.molviewbackend.entity.PdbData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdbDataRepository extends JpaRepository<PdbData, Integer> {
}