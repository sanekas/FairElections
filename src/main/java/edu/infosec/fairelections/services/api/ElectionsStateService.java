package edu.infosec.fairelections.services.api;

public interface ElectionsStateService {
    ElectionsState getState();
    void updateElectionsState();
}
