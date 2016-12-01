package edu.infosec.fairelections.model.entities.forms;

import edu.infosec.fairelections.model.api.Vote;

import javax.validation.constraints.NotNull;

public class VoterForm {
    @NotNull
    private String vote = Vote.EMPTY.name();

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VoterForm{" +
                "vote=" + vote +
                '}';
    }
}
