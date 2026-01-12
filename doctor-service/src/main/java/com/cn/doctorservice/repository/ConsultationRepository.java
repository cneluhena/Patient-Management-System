package com.cn.doctorservice.repository;

import com.cn.doctorservice.entity.ConsultationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationSession, UUID> {

    @Modifying
    @Transactional
    @Query("""
        UPDATE ConsultationSession cs
        SET cs.bookedNumOfPatients = cs.bookedNumOfPatients + 1
        WHERE cs.id = :sessionId
          AND ((cs.maxNumOfPatients - cs.bookedNumOfPatients) > 0)
    """)
    int reserveSession(@Param("sessionId") UUID sessionId);

}
