package use_case.algorithm;

import use_case.compareTeam.CompareOutputData;

public interface AlgorithmOutputBoundary {
    public void prepareSuccessView(AlgorithmOutputData outputData);
    public void prepareFailView(String problem);
}
