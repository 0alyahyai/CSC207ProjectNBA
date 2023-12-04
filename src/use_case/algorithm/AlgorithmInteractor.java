package use_case.algorithm;

import data_access.APIinterface;
import entity.Player;
import entity.Team;
import use_case.algorithm.viewAlgorithm.AlgorithmDataAccessInterfaceApi;
import use_case.entity_helpers.MarkovPlayerEvaluator;
import use_case.entity_helpers.PlayerEvaluatorFactory;
import use_case.entity_helpers.TeamEvaluator;
import use_case.entity_helpers.TeamEvaluatorFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AlgorithmInteractor implements AlgorithmInputBoundary{

    public AlgorithmOutputBoundary algorithmOutputBoundary;
    public AlgorithmDataAccessInterface algorithmDataAccessInterface;
    public  APIinterface apiDAO;

    public AlgorithmInteractor(AlgorithmOutputBoundary algorithmOutputBoundary,
                               AlgorithmDataAccessInterface algorithmDataAccessInterface,
                               APIinterface apiDAO){
        this.algorithmOutputBoundary = algorithmOutputBoundary;
        this.algorithmDataAccessInterface = algorithmDataAccessInterface;
        this.apiDAO = apiDAO;


    }

    @Override
    public void execute(String otherTeamName, String algorithm) {

        // Here we need to do several things. First, notice that we have the name of the team and the desired algorithm.
        // [x]So, first we want to get the team.
        // Also, we want to compare. [x]To do that, we will create a function that searches the requiered information for
        // Then, we will compare the two obtained values. In other words, we will...
        try {
              ArrayList<Team> teams = this.algorithmDataAccessInterface.getteams(otherTeamName);//This will get the 2 teams.
              String activePlayerName = this.algorithmDataAccessInterface.getActiveName();
              Team activeTeam = teams.get(0);
              Team otherTeam = teams.get(1);
              String otherTeamUser = this.algorithmDataAccessInterface.findUserByTeam(otherTeamName);
              if (algorithm.equals("Markov prediction")){
                  PlayerEvaluatorFactory playerEvaluatorFactory = new PlayerEvaluatorFactory();
                  TeamEvaluatorFactory teamEvaluatorFactory = new TeamEvaluatorFactory(playerEvaluatorFactory);
                  TeamEvaluator teamEvaluator = teamEvaluatorFactory.getMarkovTeamEvaluator(apiDAO);
                  Float activeTeamScore = teamEvaluator.evaluateTeam(activeTeam);
                  Float otherTeamScore = teamEvaluator.evaluateTeam(otherTeam);
                  //String activePlayerName, String otherTeamName, String otherPlayerName
//                  Float score1, Float score2, String algorithm, String activeTeamName, String activePlayerName, String otherTeamName, String otherPlayerName
                  AlgorithmOutputData algorithmOutputData = new AlgorithmOutputData(activeTeamScore, otherTeamScore, algorithm, activeTeam.getTeamName() ,activePlayerName, otherTeamName,otherTeamUser);
                  algorithmOutputBoundary.prepareSuccessView(algorithmOutputData);

              }
              else {
                  //This means that "Logarithmic comparison"
                  PlayerEvaluatorFactory playerEvaluatorFactory = new PlayerEvaluatorFactory();
                  TeamEvaluatorFactory teamEvaluatorFactory = new TeamEvaluatorFactory(playerEvaluatorFactory);
                  TeamEvaluator teamEvaluator = teamEvaluatorFactory.getLogarithmTeamEvaluator(apiDAO);
                  Float activeTeamScore = teamEvaluator.evaluateTeam(activeTeam);
                  Float otherTeamScore = teamEvaluator.evaluateTeam(otherTeam);
                  AlgorithmOutputData algorithmOutputData = new AlgorithmOutputData(activeTeamScore, otherTeamScore, algorithm, activeTeam.getTeamName() ,activePlayerName, otherTeamName,otherTeamUser);
                  algorithmOutputBoundary.prepareSuccessView(algorithmOutputData);
              }
        } catch (IOException e){
            algorithmOutputBoundary.prepareFailView("There was a problem");
        }
    }
}
