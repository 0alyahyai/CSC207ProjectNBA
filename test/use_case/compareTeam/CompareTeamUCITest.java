package use_case.compareTeam;

// JUnit 5 imports
import app.CompareUseCaseFactory;
import app.LoggedInViewFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Other imports
import data_access.MockDAO;
import entity.*;
import entity.PlayerFactory;
import use_case.compareTeam.interface_adapter.CompareController;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import use_case.compareTeam.viewCompareTeam.CompareViewOptions;
import use_case.make_team.MakeTeamDAI;
import use_case.make_team.save_team.*;
import view.ViewManagerModel;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.List;

class CompareTeamUCITest {

    private CompareDataAccessInterface databaseDAO;

    @BeforeEach
    void setUp() {
        databaseDAO = new MockDAO();
    }

    @Test
    void successTest() {

        CompareOutputBoundary compareOutputBoundary = new CompareOutputBoundary() {
            @Override
            public void prepareSuccessView(CompareOutputData outputData) {
                List<Team> teams = outputData.getTeams();
                boolean activeUserHasTeam = outputData.getactiveHasTeam();
                assert(teams.size() >= 2);
                assert(activeUserHasTeam);
            }

            @Override
            public void prepareFailView(String problem, Boolean b) {

            }
        };

        CompareInteractor compareInteractor = new CompareInteractor(
                compareOutputBoundary,
                databaseDAO
        );

        compareInteractor.execute();




    }

    @Test
    void successTest2() {

        CompareInputData compareInputData = new  CompareInputData();


        CompareViewOptions compareView = CompareUseCaseFactory.create(
                new CompareViewModel()
        );

        CompareController compareController = LoggedInViewFactory.createCompareTeamUseCase(
               new ViewManagerModel(), new CompareViewModel(), databaseDAO
        );

        compareController.execute();

    }


}