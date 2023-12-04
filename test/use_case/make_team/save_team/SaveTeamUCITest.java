package use_case.make_team.save_team;

// JUnit 5 imports
import data_access.FileUserDataAccessObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Other imports
import data_access.MockDAO;
import entity.*;
import use_case.make_team.MakeTeamDAI;

import java.io.IOException;
import java.util.List;

class SaveTeamUCITest {

    private UserFactory userFactory;
    private PlayerFactory playerFactory;

    private TeamFactory teamFactory;

    private MakeTeamDAI dao;

    SaveTeamUCITest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();
        playerFactory = new CommonPlayerFactory();
        teamFactory = new CommonTeamFactory();
        dao = new MockDAO();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void successTest() {
        User user = userFactory.createMockUser();
        ((MockDAO) dao).save(user);
        ((MockDAO) dao).setActiveUser(user);

        List<Player> players = playerFactory.generateMockTeam();
        String teamName = "Jets";
        SaveTeamInputData inputData = new SaveTeamInputData(user, players, teamName);
        Team expectedTeam = teamFactory.createTeam(teamName, players);



        SaveTeamOutputBoundary outputBoundary = new SaveTeamOutputBoundary() {

            @Override
            public void prepareSuccessView(SaveTeamOutputData outputData) {
                Team returnedTeam = outputData.getTeam();
                User returnedUser = outputData.getUser();
                assert(outputData.getTeam().getTeamPlayers().equals(players));
                assert(returnedUser.equals(user));

                assert(returnedTeam.equals(expectedTeam)); // Note usage of overridden equals method

                Team persistedTeam = dao.getTeamOfUser(user.getUserName());
                assert(expectedTeam.equals(persistedTeam)); // Note usage of overridden equals method

            }

            @Override
            public void prepareFailView(String problem) {
                Assertions.fail("Should not fail.");
            }
        };


        SaveTeamInputBoundary interactor = new SaveTeamUCI(dao, outputBoundary, teamFactory);

        interactor.execute(inputData);


    }
}