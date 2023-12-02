package use_case.make_team.player_stats.interface_adapater;

import use_case.view_team.interface_adapter.ViewTeamState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerStatsViewModel extends ViewModel {

    public static String TITLE_LABEL = "Player Statistics";

    public static final String BACK_BUTTON_LABEL = "Back";

    private PlayerStatsState playerStatsState = new PlayerStatsState();

    public PlayerStatsViewModel() {
        super("Player Statistics");
    }

    public void setState(PlayerStatsState playerStatsState) {
        this.playerStatsState  = playerStatsState;
    }

    public PlayerStatsState getState() {
        return playerStatsState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.playerStatsState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

