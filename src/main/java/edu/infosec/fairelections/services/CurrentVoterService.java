package edu.infosec.fairelections.services;

import java.util.concurrent.atomic.AtomicLong;

public interface CurrentVoterService {
    boolean canAccessVoter(CurrentVoter currentVoter, AtomicLong voterId);
}
