package use_case.view_team.interface_adapter;

import java.util.Map;

public class ViewTeamState {

    private String viewTeamError = null;

    //An array containing the stats for each player on the team
    // The array is in the following order: ["Player's Team Name", "Player name", "Points Per Game",
    //                "Assists Per Game", "Rebounds Per Game", "Steals Per Game", "Blocks Per Game"]

    private String[] player1Stats;

    private String[] player2Stats;

    private String[] player3Stats;

    private String[] player4Stats;

    private String[] player5Stats;

    private String message = null;

    // TODO: Clean up code
    public ViewTeamState(ViewTeamState copy) {
        viewTeamError = copy.viewTeamError;
        message = copy.message;
    }

    public ViewTeamState() {}

    public void setPlayerNStats(int n, String[] stats) {
        switch (n) {
            case 1 -> player1Stats = stats;
            case 2 -> player2Stats = stats;
            case 3 -> player3Stats = stats;
            case 4 -> player4Stats = stats;
            case 5 -> player5Stats = stats;
        }
    }

    public String[] getPlayerNStats(int n) {
        return switch (n) {
            case 1 -> player1Stats;
            case 2 -> player2Stats;
            case 3 -> player3Stats;
            case 4 -> player4Stats;
            case 5 -> player5Stats;
            default -> null;
        };
    }


    public String getViewTeamError() {
        return viewTeamError;
    }

    public void setViewTeamError(String viewTeamError) {
        this.viewTeamError = viewTeamError;
        this.message = viewTeamError;
    }


    public String getMessage() {
        return message;
    }

}
