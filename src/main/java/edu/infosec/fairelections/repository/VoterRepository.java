package edu.infosec.fairelections.repository;

import edu.infosec.fairelections.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface VoterRepository extends JpaRepository<Voter, AtomicLong> {
    Optional<Voter> findOneByEmail(String email);
}
