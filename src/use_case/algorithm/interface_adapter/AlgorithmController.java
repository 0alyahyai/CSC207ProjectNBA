package use_case.algorithm.interface_adapter;

import use_case.algorithm.AlgorithmInputBoundary;
import use_case.compareTeam.CompareInputBoundary;

public class AlgorithmController{
    public final AlgorithmInputBoundary algorithmInputBoundary;
    public  final String algorithm;
    public final String team;

    public AlgorithmController(AlgorithmInputBoundary algorithmInputBoundary, String algorithm, String team) {
        this.algorithmInputBoundary = algorithmInputBoundary;
        this.algorithm = algorithm;
        this.team = team;
    }

    public void execute(){
        algorithmInputBoundary.execute(algorithm, team);
    }
}
