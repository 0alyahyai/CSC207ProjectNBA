package use_case.make_team.create_team.search_player;

public class SearchPlayerController {
    private final SearchPlayerInputBoundary searchPlayerInputBoundary;

    public SearchPlayerController(SearchPlayerInputBoundary searchPlayerInputBoundary) {
        this.searchPlayerInputBoundary = searchPlayerInputBoundary;
    }

    public void execute(String writtenPlayerName) {

        searchPlayerInputBoundary.execute();
    }


}
