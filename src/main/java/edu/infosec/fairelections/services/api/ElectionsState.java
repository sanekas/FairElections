package edu.infosec.fairelections.services.api;

public enum ElectionsState {
    NOT_STARTED("Start elections", "Elections are about to begin", false),
    RUNNING("End elections", "Elections are running", true),
    ENDED("Elections are over", null, false); //TODO: make this better, replace null by smth

    private final String buttonTitle;
    private final String stateMessage;
    private final boolean votingOpened;

    ElectionsState(String buttonTitle, String stateMessage, boolean votingOpened) {
        this.buttonTitle = buttonTitle;
        this.stateMessage = stateMessage;
        this.votingOpened = votingOpened;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public String getStateMessage() {
        return stateMessage;
    }

    public boolean isVotingOpened() {
        return votingOpened;
    }
}
