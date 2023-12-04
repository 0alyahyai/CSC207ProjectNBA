package use_case.algorithm.interface_adapter;

import use_case.algorithm.AlgorithmInputBoundary;
import use_case.compareTeam.CompareInputBoundary;

public class AlgorithmController{
    public final AlgorithmInputBoundary algorithmInputBoundary;

    public AlgorithmController(AlgorithmInputBoundary algorithmInputBoundary) {
        this.algorithmInputBoundary = algorithmInputBoundary;
    }

    public void execute(String algorithm, String team){
        algorithmInputBoundary.execute(algorithm, team);
    }
}
