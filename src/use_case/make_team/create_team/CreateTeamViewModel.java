package use_case.make_team.create_team;

import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("create-team-state", null, state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
