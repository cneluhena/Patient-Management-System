package com.cn.appointmentservice.repository;

import com.cn.appointmentservice.entity.ConsultationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationSession, UUID> {

}
