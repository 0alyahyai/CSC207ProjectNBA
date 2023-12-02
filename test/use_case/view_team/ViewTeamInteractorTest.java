package use_case.view_team;

import data_access.APIinterface;
import entity.CommonTeamFactory;
import entity.CommonUserFactory;
import entity.Team;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import net.bytebuddy.*;

//bytebuddy agent for mocking
import net.bytebuddy.agent.ByteBuddyAgent;

import java.util.ArrayList;

class ViewTeamInteractorTest {


    @Mock
    private ViewTeamUserDataAccessInterface mockUserDataAccessObject;

    @Mock
    private APIinterface mockApi;

    @InjectMocks
    private ViewTeamInteractor viewTeamInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void userHasNoTeamTest() {
        User user = new CommonUserFactory().createMockUser();
        //user has no team, thus mock returns null
        when(mockUserDataAccessObject.getActiveUser()).thenReturn(user);

        ViewTeamOutputBoundary outputBoundary = new ViewTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewTeamOutputData outputData) {
                fail("Should not have called this method");
            }

            @Override
            public void prepareFailview(ViewTeamOutputData outputData) {
                assertEquals(outputData.getFailureMessage(),
                        "You do not have a team yet! Create a team by going to the 'Create Team' tab.");
            }
        };
        viewTeamInteractor = new ViewTeamInteractor(outputBoundary, mockUserDataAccessObject, mockApi);
        viewTeamInteractor.execute();
    }

    @Test
    void userHasTeamTest() {
        User user = new CommonUserFactory().createMockUser();
        //make team factory
        CommonTeamFactory teamFactory = new CommonTeamFactory();
        //make mock team
        Team mockTeam = teamFactory.createMockTeam();
        //set user's team to mock team
        user.setTeam(mockTeam);
        //mock user data access object to return user
        when(mockUserDataAccessObject.getActiveUser()).thenReturn(user);
        //mock api to return dummy data
        when(mockApi.getNameOfPlayer(anyInt())).thenReturn("Player");

        //create mock stats
        ArrayList<String> mockStats = new ArrayList<String>();
        mockStats.add("Team");
        mockStats.add("Name");
        mockStats.add("Points");
        mockStats.add("Assists");
        mockStats.add("Rebounds");
        mockStats.add("Steals");
        mockStats.add("Blocks");
        //mock api to return mock stats
        when(mockApi.viewTeamGetStats(anyInt())).thenReturn(mockStats);

        //create mock output data
        ViewTeamOutputData outputData = new ViewTeamOutputData();
        //set mock output data
        outputData.setPlayerNStats(1, mockStats);
        outputData.setPlayerNStats(2, mockStats);
        outputData.setPlayerNStats(3, mockStats);
        outputData.setPlayerNStats(4, mockStats);
        outputData.setPlayerNStats(5, mockStats);


        //mock output boundary to check if output data is correct
        ViewTeamOutputBoundary outputBoundary = new ViewTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewTeamOutputData outputData) {
                assertEquals(outputData.getPlayerNStats(1), mockStats);
                assertEquals(outputData.getPlayerNStats(2), mockStats);
                assertEquals(outputData.getPlayerNStats(3), mockStats);
                assertEquals(outputData.getPlayerNStats(4), mockStats);
                assertEquals(outputData.getPlayerNStats(5), mockStats);
            }

            @Override
            public void prepareFailview(ViewTeamOutputData outputData) {
                fail("Should not have called this method");
            }
        };
        //make the interactor use the mock output boundary
        viewTeamInteractor = new ViewTeamInteractor(outputBoundary, mockUserDataAccessObject, mockApi);
        //execute the interactor
        viewTeamInteractor.execute();



    }
}