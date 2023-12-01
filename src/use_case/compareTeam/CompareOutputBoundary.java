package use_case.compareTeam;

public interface CompareOutputBoundary {

    public void prepareSuccessView(CompareOutputData outputData);
    public void prepareFailView(String problem);
}
