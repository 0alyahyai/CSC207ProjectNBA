package use_case.algorithm.interface_adapter;

import entity.Team;

import java.util.List;

public class AlgorithmState {

    private final Float scoreActive;
    private final Float scoreOther;

    public  final String activeTeamName;
    public final String activePlayerName;

    public final String otherTeamName;

    public final String otherPlayerName;

    private final String algorithm;
    public AlgorithmState(Float scoreActive, Float scoreOther, String algorithm, String activeTeamName, String activePlayerName, String otherTeamName, String otherPlayerName ) {
        this.scoreActive = scoreActive;
        this.scoreOther = scoreOther;
        this.algorithm = algorithm;
        this.activeTeamName = activeTeamName;
        this.activePlayerName = activePlayerName;
        this.otherTeamName = otherTeamName;
        this.otherPlayerName = otherPlayerName;
    }


    public Float getScoreActiveTeam() {
        return scoreActive;
    }
    public Float getScoreOther(){return scoreOther;}

    public Float getOtherActiveTeam() {
        return scoreOther;
    }

    public String getAlgorithm(){return algorithm;}

    public  String getActivePlayerName(){return activePlayerName;}
    public  String getActiveTeamName(){return activeTeamName;}

    public  String getOtherTeamName(){return otherTeamName;}
    public  String getOtherPlayerName(){return otherPlayerName;}


}
