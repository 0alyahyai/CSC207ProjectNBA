package use_case.make_team.save_team;

import entity.Player;
import entity.Team;
import entity.TeamFactory;
import entity.User;
import use_case.make_team.MakeTeamDAI;

import java.util.List;

public class SaveTeamUCI implements SaveTeamInputBoundary{

    private final MakeTeamDAI dao;
    private final SaveTeamOutputBoundary outputBoundary;
    private final TeamFactory teamFactory;

    public SaveTeamUCI(MakeTeamDAI dao, SaveTeamOutputBoundary outputBoundary, TeamFactory teamFactory) {
        this.dao = dao;
        this.outputBoundary = outputBoundary;
        this.teamFactory = teamFactory;
    }

    @Override
    public void execute(SaveTeamInputData inputData) {
        User user = inputData.getUser();
        List<Player> players = inputData.getPlayers();
        String teamName = inputData.getTeamName();

        Team newTeam = teamFactory.createTeam(teamName, players);

        // TODO: can refactor SaveTeamInputData to not include a user, since we are now getting it from the DAO
        boolean success = dao.saveTeam(dao.getActiveUser(), newTeam);

        if (success) {
            SaveTeamOutputData outputData = new SaveTeamOutputData(user, newTeam);
            outputBoundary.prepareSuccessView(outputData);
        }
        else {
            String problemString = "There was a problem with saving your team. Please try again." ;
            outputBoundary.prepareFailView(problemString);
        }

    }
}
