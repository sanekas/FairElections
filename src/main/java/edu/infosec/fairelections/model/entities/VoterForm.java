package edu.infosec.fairelections.model.entities;

import edu.infosec.fairelections.model.api.Vote;

import javax.validation.constraints.NotNull;

public class VoterForm {
    @NotNull
    private Long id;

    @NotNull
    private Vote vote;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VoterForm{" +
                "id=" + id +
                ", vote=" + vote +
                '}';
    }
}
