package use_case.view_team;

import entity.Player;
import entity.Team;
import entity.User;
import data_access.APIinterface;

import java.util.ArrayList;
import java.util.List;

public class ViewTeamInteractor implements ViewTeamInputBoundary{

    private final ViewTeamOutputBoundary viewTeamOutputBoundary;

    private final ViewTeamUserDataAccessInterface userDataAccessObject;

    private final APIinterface api;

    public ViewTeamInteractor(ViewTeamOutputBoundary viewTeamOutputBoundary,
                              ViewTeamUserDataAccessInterface userDataAccessObject, APIinterface apiDataAccessObject) {
        this.viewTeamOutputBoundary = viewTeamOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
        this.api = apiDataAccessObject;

    }

    @Override
    public void execute() {


        User current_user = userDataAccessObject.getActiveUser();

        // this is how we will get the team in the final implementation
        // TODO: uncomment this when we have this implemented
         Team team = current_user.getUserTeam();

         // to be replaced with team iterator
        if (team == null) {
            ViewTeamOutputData outputData = new ViewTeamOutputData();
            outputData.setFailureMessage("You do not have a team yet! Create a team by going to the 'Create Team' tab.");
            viewTeamOutputBoundary.prepareFailview(outputData);
        } else { // team exists
            System.out.println(team);
            System.out.println(team.getTeamPlayers());

            ViewTeamOutputData outputData = new ViewTeamOutputData();
            List<Player> players = team.getTeamPlayers();
            for (int i = 0; i < 5; i++) {
                Player player = players.get(i);
                int id = player.getPlayerID();
                String name = api.getNameOfPlayer(id);
                ArrayList<String> stats = api.viewTeamGetStats(id);
                outputData.setPlayerNStats(i + 1, stats);
            }
            viewTeamOutputBoundary.prepareSuccessView(outputData);

        }



    }

}
