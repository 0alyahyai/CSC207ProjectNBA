package use_case.make_team.create_team;

import entity.Player;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class CreateTeamViewModel extends ViewModel {
    public static final String VIEW_NAME = "Create Team View";

    public final static String TEAM_NAME_LABEL = "Team Name: ";
    public final static String PLAYER_SEARCH_LABEL = "Player (to search): ";
    public final static String SEARCH_BUTTON_LABEL = "Search";
    public final static String STATS_BUTTON_LABEL = "Stats";
    public final static String ADD_PLAYER_BUTTON_LABEL = "Add";
    public final static String SUBMIT_BUTTON_LABEL = "Submit";

    // Instance Variables
    private CreateTeamState state;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CreateTeamViewModel() {
        super(VIEW_NAME);
        state = new CreateTeamState();
    }
    public CreateTeamViewModel(CreateTeamState state) {
        super(VIEW_NAME);
        this.state = state;
    }



    public CreateTeamState getState() {
        return state;
    }

    public void setState(CreateTeamState state) {
        this.state = state;
    }

    public void setSearchResults(List<Player> matchingPlayers) {
        this.state.setMatchingPlayers(matchingPlayers);
        propertyChangeSupport.firePropertyChange("player-matches", null, matchingPlayers);
    }

    public void setCurrentTeam(List<Player> teamSoFar) {
        this.state.setTeamSoFar(teamSoFar);
        propertyChangeSupport.firePropertyChange("updated-team", null, teamSoFar);
    }

    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("create-team-state", null, state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void clearState() {
        state.clear();
        propertyChangeSupport.firePropertyChange("state-clearance", null, state);
    }

//    public static PlayerListToListOfStrings
}
