package com.snazzyrobot.molviewbackend.repository;

import com.snazzyrobot.molviewbackend.entity.AtomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtomInfoRepository extends JpaRepository<AtomInfo, Long> {
}