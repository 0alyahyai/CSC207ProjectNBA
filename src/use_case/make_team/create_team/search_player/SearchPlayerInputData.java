package use_case.make_team.create_team.search_player;

import entity.User;

public class SearchPlayerInputData {

    private final String writtenPlayerName;

    public SearchPlayerInputData(String writtenPlayerName) {
        this.writtenPlayerName = writtenPlayerName;
    }

    public String getWrittenPlayerName() {
        return writtenPlayerName;
    }



}
