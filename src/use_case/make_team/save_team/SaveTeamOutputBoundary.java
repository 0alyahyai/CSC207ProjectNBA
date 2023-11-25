package use_case.make_team.save_team;

public interface SaveTeamOutputBoundary {
    void prepareSuccessView(SaveTeamOutputData outputData);

    void prepareFailView(String problem);
}
