package edu.infosec.fairelections.model.entities;

import edu.infosec.fairelections.model.api.Vote;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class VoterForm {
    @NotNull
    private Long id;

    @NotNull
    private Vote vote;

    @NotNull
    private List<Vote> options;

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

    public List<Vote> getOptions() {
        return options;
    }

    public void setOptions() {
        this.options = Arrays.asList(Vote.values());
    }

    @Override
    public String toString() {
        return "VoterForm{" +
                "id=" + id +
                ", vote=" + vote +
                '}';
    }
}
