package edu.infosec.fairelections.model.entities;

import edu.infosec.fairelections.model.api.Vote;

import javax.persistence.*;

@Entity
@Table(name = "voter")
public class Voter {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_vote", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vote vote;

    @Column(name = "twin_voter_id")
    private Long twinVoterId;

    public Long getId() {
        return id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Long getTwinVoterId() {
        return twinVoterId;
    }

    public void setTwinVoterId(Long twinVoterId) {
        this.twinVoterId = twinVoterId;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id=" + id +
                ", vote=" + vote +
                ", twinVoterId=" + twinVoterId +
                '}';
    }
}
