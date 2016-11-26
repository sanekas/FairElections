package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.services.api.SelectionsState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SelectionsStateService {
    private SelectionsState state = SelectionsState.NOT_STARTED;

    public SelectionsState getState() {
        return state;
    }

    public void setState(SelectionsState state) {
        this.state = state;
    }
}
