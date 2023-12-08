package use_case.algorithm;

import data_access.APIDataAccessObject;
import data_access.APIinterface;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.algorithm.interface_adapter.AlgorithmController;
import use_case.algorithm.interface_adapter.AlgorithmPresenter;
import use_case.algorithm.interface_adapter.AlgorithmViewModel;
import view.ViewManagerModel;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmInteractorTest {
    private AlgorithmDataAccessInterface databaseDAO;
    private APIinterface apiDAO;

    @BeforeEach
    void setUp() throws IOException {
        apiDAO = new APIDataAccessObject();
        databaseDAO = new FileUserDataAccessObject(
                "./users.csv", new CommonUserFactory(), apiDAO
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUnit() {
        AlgorithmInputData inputData = new AlgorithmInputData();

        User someUser = ((FileUserDataAccessObject) databaseDAO).get("mimis");

        ((FileUserDataAccessObject) databaseDAO).setActiveUser(
                someUser
        );

        String algorithm = "Markov prediction";
        String team = "woahh";

        AlgorithmOutputBoundary algorithmOutputBoundary = new AlgorithmOutputBoundary() {
            @Override
            public void prepareSuccessView(AlgorithmOutputData outputData) {
                String retrievedAlgorithm = outputData.getAlgorithm();
                float activeScore = outputData.getActiveScore();
                String activeTeamName = outputData.getActiveTeamName();
                String otherPlayerName = outputData.getOtherPlayerName();
                String otherTeamName = outputData.getOtherTeamName();
                String activePlayerName = outputData.getActivePlayerName();

                assert(algorithm.equals(retrievedAlgorithm));
                assert(otherTeamName.equals(team));
            }

            @Override
            public void prepareFailView(String problem) {

            }
        };

        AlgorithmInputBoundary algorithmUCI = new AlgorithmInteractor(
                algorithmOutputBoundary, databaseDAO, apiDAO
        );

        algorithmUCI.execute(team, algorithm);

        AlgorithmController controller = new AlgorithmController(algorithmUCI);
        controller.execute(team, algorithm);

        AlgorithmOutputBoundary outputBoundary = new AlgorithmPresenter(
               new ViewManagerModel(), new AlgorithmViewModel()
        );

        outputBoundary.prepareSuccessView(new AlgorithmOutputData(
                (float) 0.0, (float) 0.0, null, null, null, null,
                null
        ));

        controller.execute(team, algorithm);
    }
}