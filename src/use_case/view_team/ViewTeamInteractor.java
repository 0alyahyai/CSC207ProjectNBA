package use_case.view_team;

import entity.Player;
import entity.Team;
import entity.User;
import data_access.APIinterface;

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
            ViewTeamOutputData outputData = new ViewTeamOutputData();
            List<Player> players = team.getTeamPlayers();
            for (int i = 0; i < 5; i++) {
                Player player = players.get(i);
                int id = player.getPlayerID();
                String name = api.getNameOfPlayer(id);
                String[] stats = api.viewTeamGetStats(id).toArray(new String[0]);
                outputData.setPlayerNStats(i,
                        new String[]{name, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]});
            }
            viewTeamOutputBoundary.prepareSuccessView(outputData);

        }



        //dummy data for now

//        ViewTeamOutputData outputData = new ViewTeamOutputData();
//        for (int i = 1; i <= 5; i++) {
//            outputData.setPlayerNStats(i,
//                    new String[]{String.format("Player %d", i), "1", "2", "3", "4", "5", "6"});
//
//        }
//
//        viewTeamOutputBoundary.prepareSuccessView(outputData);

    }

}
