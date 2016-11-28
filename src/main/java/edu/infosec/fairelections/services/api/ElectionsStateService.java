package edu.infosec.fairelections.services.api;

public interface ElectionsStateService {
    public ElectionsState getState();

    public void setState(ElectionsState state);
}
