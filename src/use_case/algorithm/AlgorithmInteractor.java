package use_case.algorithm;

import entity.Player;
import entity.Team;
import use_case.algorithm.viewAlgorithm.AlgorithmDataAccessInterfaceApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AlgorithmInteractor implements AlgorithmInputBoundary{

    public AlgorithmOutputBoundary algorithmOutputBoundary;
    public AlgorithmDataAccessInterface algorithmDataAccessInterface;
    public AlgorithmDataAccessInterfaceApi algorithmDataAccessInterfaceApi;

    public AlgorithmInteractor(AlgorithmOutputBoundary algorithmOutputBoundary,
                               AlgorithmDataAccessInterface algorithmDataAccessInterface,
                               AlgorithmDataAccessInterfaceApi algorithmDataAccessInterfaceApi){
        this.algorithmOutputBoundary = algorithmOutputBoundary;
        this.algorithmDataAccessInterface = algorithmDataAccessInterface;
        this.algorithmDataAccessInterfaceApi = algorithmDataAccessInterfaceApi;


    }

    @Override
    public void execute(String algorithm, String otherTeamName) {

        // Here we need to do several things. First, notice that we have the name of the team and the desired algorithm.
        // [x]So, first we want to get the team.
        // Also, we want to compare. [x]To do that, we will create a function that searches the requiered information for
        // Then, we will compare the two obtained values. In other words, we will...
        try {
              ArrayList<Team> teams = this.algorithmDataAccessInterface.getteams(otherTeamName);  //This will get the 2 teams.
              Team activeTeam = teams.get(0);
              Team otherTeam = teams.get(0);

              TeamC








//            ArrayList<Player> playersThisTeam = (ArrayList<Player>) teams.get(0).getTeamPlayers();
//            ArrayList<Player> playersOtherTeam = (ArrayList<Player>) teams.get(0).getTeamPlayers();
//
//            ArrayList<Map<Object, Object>> playersFirstTeamStats = new ArrayList<>();
//            ArrayList<Map<Object, Object>> playersSecondTeamStats = new ArrayList<>();
//
//
//
//            for(int i = 0; i < playersThisTeam.size(); i++){ //the size should be 5, but just in case
//                playersFirstTeamStats.add(this.algorithmDataAccessInterfaceApi.getData(playersThisTeam.get(i).getPlayerID()));
//            }
//            for(int i = 0; i < playersOtherTeam.size(); i++){ //the size should be 5, but just in case
//                playersSecondTeamStats.add(this.algorithmDataAccessInterfaceApi.getData(playersOtherTeam.get(i).getPlayerID()));
//            }
//
//            //Here we have the big maps, as in Docs. We want to get the information. [MAP1, MAP2, MAP3, MAP4, MAP5] (ASI)
//            // we want to send this to the given algorithm.


        } catch (IOException e){
            //this is not necessary because el otro nos garantiza que este ya reciba dos cosas.
        }






    }

}
