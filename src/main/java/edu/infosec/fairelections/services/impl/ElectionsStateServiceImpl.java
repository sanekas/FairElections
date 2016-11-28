package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.controllers.UserController;
import edu.infosec.fairelections.services.api.ElectionsState;
import edu.infosec.fairelections.services.api.ElectionsStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ElectionsStateServiceImpl implements ElectionsStateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private ElectionsState state = ElectionsState.NOT_STARTED;

    public ElectionsState getState() {
        return state;
    }

    public void updateElectionsState() {
        switch (state) {
            case NOT_STARTED:
                state = ElectionsState.RUNNING;
                LOGGER.info("Elections started!");
                break;
            case RUNNING:
                state = ElectionsState.ENDED;
                LOGGER.info("Elections ended!");
                break;
            default:
                LOGGER.info("Incorrect elections state update query.");
        }

    }
}
