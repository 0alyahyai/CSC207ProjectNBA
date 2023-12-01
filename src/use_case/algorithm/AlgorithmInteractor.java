package use_case.algorithm;

import entity.Team;
import use_case.compareTeam.CompareDataAccessInterface;
import use_case.compareTeam.CompareOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;

public class AlgorithmInteractor implements AlgorithmInputBoundary{

    public AlgorithmOutputBoundary algorithmOutputBoundary;
    public AlgorithmDataAccessInterface algorithmDataAccessInterface;

    public AlgorithmInteractor(AlgorithmOutputBoundary algorithmOutputBoundary,
                               AlgorithmDataAccessInterface algorithmDataAccessInterface){
        this.algorithmOutputBoundary = algorithmOutputBoundary;
        this.algorithmDataAccessInterface = algorithmDataAccessInterface;

    }

    @Override
    public void execute(String algorithm, String otherTeamName) {

        // Here we need to do several things. First, notice that we have the name of the team and the desired algorithm.
        // [x]So, first we want to get the team.
        // Also, we want to compare. [x]To do that, we will create a function that searches the requiered information for
        // Then, we will compare the two obtained values. In other words, we will...
        try {
            ArrayList<Team> teams = this.algorithmDataAccessInterface.getteams(otherTeamName);  //This will get the 2 teams.
            this.algorithmDataAccessInterface.getData(teams.get(0));
            this.algorithmDataAccessInterface.getData(teams.get(1));

        } catch (IOException e){
            //this is not necessary because el otro nos garantiza que este ya reciba dos cosas.
        }






    }

}
