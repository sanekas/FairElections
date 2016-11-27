package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.services.api.ElectionsState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ElectionsStateService {
    private ElectionsState state = ElectionsState.NOT_STARTED;

    public ElectionsState getState() {
        return state;
    }

    public void setState(ElectionsState state) {
        this.state = state;
    }
}
