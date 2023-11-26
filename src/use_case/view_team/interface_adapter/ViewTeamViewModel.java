package use_case.view_team.interface_adapter;

import use_case.view_team.view.ViewTeamView;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewTeamViewModel extends ViewModel {

    public static final String TITLE_LABEL = "View Team View";

    public static final String BACK_BUTTON_LABEL = "Back";

    private ViewTeamState state = new ViewTeamState();

    public ViewTeamViewModel() {
        super("view team");
    }

    public void setState(ViewTeamState state) {
        this.state = state;
    }

    public ViewTeamState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
