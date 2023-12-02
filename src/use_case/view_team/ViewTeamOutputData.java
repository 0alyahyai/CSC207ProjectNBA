package use_case.view_team;

import java.util.ArrayList;

public class ViewTeamOutputData {

    private ArrayList<String> player1Stats;

    private ArrayList<String> player2Stats;

    private ArrayList<String> player3Stats;

    private ArrayList<String> player4Stats;

    private ArrayList<String> player5Stats;

    private String failureMessage;

    public ViewTeamOutputData() {
    }


    public void setPlayerNStats(int n, ArrayList<String> stats) {
        switch (n) {
            case 1 -> player1Stats = stats;
            case 2 -> player2Stats = stats;
            case 3 -> player3Stats = stats;
            case 4 -> player4Stats = stats;
            case 5 -> player5Stats = stats;
        }
    }

    public ArrayList<String> getPlayerNStats(int n) {
        return switch (n) {
            case 1 -> player1Stats;
            case 2 -> player2Stats;
            case 3 -> player3Stats;
            case 4 -> player4Stats;
            case 5 -> player5Stats;
            default -> null;
        };
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getFailureMessage() {
        return failureMessage;
    }
}
