package use_case.view_team;

public class ViewTeamOutputData {

    private String[] player1Stats;

    private String[] player2Stats;

    private String[] player3Stats;

    private String[] player4Stats;

    private String[] player5Stats;

    private String failureMessage;

    public ViewTeamOutputData() {
    }

    public ViewTeamOutputData(String failureMessage) {

    }

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

    public String getFailureMessage() {
        return failureMessage;
    }
}
