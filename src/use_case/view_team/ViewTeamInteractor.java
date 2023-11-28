package use_case.view_team;

import entity.User;
import data_access.APIinterface;

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
        // Team team = current_user.getUserTeam();


//        if (team == null) {
//            viewTeamOutputBoundary.prepareFailview(new ViewTeamOutputData("You do not have a team yet."));
//        } else {
//            List<Player> players = team.getTeamPlayers();
//            ViewTeamOutputData outputData = new ViewTeamOutputData();
//            for (int i = 1; i <= 5; i++) {
//                outputData.setPlayerNStats(i, api.viewTeamGetStats(players.get(i - 1)));
//            }

        //dummy data for now

        ViewTeamOutputData outputData = new ViewTeamOutputData();
        for (int i = 1; i <= 5; i++) {
            outputData.setPlayerNStats(i,
                    new String[]{String.format("Player %d", i), "1", "2", "3", "4", "5", "6"});

        }
        viewTeamOutputBoundary.prepareSuccessView(outputData);


    }

}
