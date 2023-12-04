package use_case.algorithm;

import entity.Team;

import java.util.List;

public class AlgorithmOutputData {

    private final Float scoreActive;
    private final Float scoreOther;
    private final String algorithm;

    private final String activeTeamName;
    private final String activePlayerName;

    private final String otherTeamName;
    private final String otherPlayerName;



    public AlgorithmOutputData( Float score1, Float score2, String algorithm, String activeTeamName, String activePlayerName, String otherTeamName, String otherPlayerName) {
        this.scoreActive = score1;
        this.scoreOther = score2;
        this.algorithm = algorithm;
        this.activeTeamName = activeTeamName;
        this.activePlayerName = activePlayerName;
        this.otherTeamName = otherTeamName;
        this.otherPlayerName = otherPlayerName;
    }

    public String getAlgorithm(){return algorithm;}

    public Float getActiveScore() {
        return scoreActive;
    }
    public Float getOtherScore() {
        return scoreOther;
    }

    public  String getActivePlayerName(){return activePlayerName;}
    public  String getActiveTeamName(){return activeTeamName;}

    public  String getOtherTeamName(){return otherTeamName;}
    public  String getOtherPlayerName(){return otherPlayerName;}

}
