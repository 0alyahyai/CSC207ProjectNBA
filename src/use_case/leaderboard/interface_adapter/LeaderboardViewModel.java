package use_case.leaderboard.interface_adapter;

import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel{

//    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String TITLE_LABEL = "Leaderboard View";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";

    private LeaderboardState state = new LeaderboardState();

    public LeaderboardViewModel() {
        super("leaderboard");
    }

    public void setState(LeaderboardState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LeaderboardState getState() {
        return state;
    }
}
