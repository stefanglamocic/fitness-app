package com.fit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Participation;
import com.fit.model.ParticipationId;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId>{

}
