package data_access;

//import all packages required to make API calls
import java.util.*;
import com.google.gson.reflect.TypeToken;
import entity.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Map;
import com.google.gson.Gson;

public class APIDataAccessObject implements APIinterface {

    //make java doc
    /**
     * This is the constructor for the APIDataAccessObject class
     */
    public APIDataAccessObject() {
    }


    @Override
    public Map<String, Object> searchPlayer(String name) {
        //create url
        String url = String.format("https://api-nba-v1.p.rapidapi.com/players?search=%s", name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", "98c71baf05mshcd7f570f965f6d0p15d3b7jsn83f75950498e")
                .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            //turn response into map
            Map<String, Object> responseMap = jsonToMap(response.body());
            return responseMap;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error: " + e);
            return null;
        }
    }

    public Map<String, Object> getPlayerStats(int id) {

        //get current year
        int year = LocalDateTime.now().getYear();
        //create url
        String url = String.format("https://api-nba-v1.p.rapidapi.com/players/statistics?id=%s&season=%s", id, year);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", "98c71baf05mshcd7f570f965f6d0p15d3b7jsn83f75950498e")
                .header("x-rapidapi-host", "api-nba-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        //make api call
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            //turn response into map
            Map<String, Object> responseMap = jsonToMap(response.body());
            return responseMap;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getGeneralPlayerInfo(int id) {
        //create url
        String url = String.format("https://api-nba-v1.p.rapidapi.com/players?id=%s", id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", "98c71baf05mshcd7f570f965f6d0p15d3b7jsn83f75950498e")
                .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            //turn response into map
            Map<String, Object> responseMap = jsonToMap(response.body());
            return responseMap;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error: " + e);
            return null;
        }
    }


    //create a helper turning the json into a map
    private Map<String, Object> jsonToMap(String json) {
        /**
         * This method is responsible for turning a json string into a map. Depends on Gson library.
         * @param json
         * @return retMap
         */
        try {
            Map<String, Object> retMap = new Gson().fromJson(json,
                    new TypeToken<TreeMap<String, Object>>() {}.getType());
            return retMap;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public Stats getStats() {
        return null;
    }

    @Override
    public String getNameOfPlayer(int id) {

        Map<String, Object> generalInfo = getGeneralPlayerInfo(id);
        ArrayList<Map<String, Object>> responseArray = (ArrayList<Map<String, Object>>) generalInfo.get("response");

        if (responseArray.size() == 0) {
            return "Player not found";
        } else {
            Map<String, Object> playerInfo = responseArray.get(0);
            return (String) playerInfo.get("firstname") + " " + (String) playerInfo.get("lastname");
        }
    }

    @Override
    public ArrayList<String> viewTeamGetStats(int id) {
        /**
         * This method is responsible for getting the stats of a player in the current season
         * @param id
         * @return ArrayList<String>
         */

        ArrayList<String> viewStats = new ArrayList<String>();

        Map<String, Object> playerStats = getPlayerStats(id);

        //create the response array
        ArrayList<Map<String, Object>> responseArray = (ArrayList<Map<String, Object>>) playerStats.get("response");

        //intialise all needed stats
        String teamName = "[Player has not played any games yet.]";
        String name = "";
        double pointsPG = 0;
        double assistsPG = 0;
        double reboundsPG = 0;
        double stealsPG = 0;
        double blocksPG = 0;

        //add the player's team name
        if (responseArray.size() != 0) {
            // get the last game played
            Map<String, Object> lastGame = responseArray.get(responseArray.size() - 1);
            //team info of the last game
            Map<String, Object> teamInfo = (Map<String, Object>) lastGame.get("team");
            teamName = (String) teamInfo.get("name");
            //adding the team name to the viewStats array

        }
        viewStats.add(teamName);

        //add the player's name
        name = getNameOfPlayer(id);
        viewStats.add(name);

        //creating the numerical stats

        for (int i = 0; i < responseArray.size(); i++) {
            Map<String, Object> game = responseArray.get(i);
            pointsPG += (double) game.get("points");
            assistsPG += (double) game.get("assists");
            reboundsPG += (double) game.get("totReb");
            stealsPG += (double) game.get("steals");
            blocksPG += (double) game.get("blocks");
        }

        //getting the averages
        if (responseArray.size() != 0) {

            float numGames = responseArray.size();
            pointsPG /= numGames;
            assistsPG /= numGames;
            reboundsPG /= numGames;
            stealsPG /= numGames;
            blocksPG /= numGames;

        }
        viewStats.add(String.format("%.2f", pointsPG));
        viewStats.add(String.format("%.2f", assistsPG));
        viewStats.add(String.format("%.2f", reboundsPG));
        viewStats.add(String.format("%.2f", stealsPG));
        viewStats.add(String.format("%.2f", blocksPG));

        return viewStats;
    }

    //Todo: The following methods I implemented for the leaderboard. We will need to find a way to set teamId, for now I just pass in the userId.
    public Player makePlayerFromId(int id){
        PlayerFactory Factory = new CommonPlayerFactory();
        return Factory.create(getNameOfPlayer(id), id);
    }

    public Team makeTeamFromIdList(List<Integer> idList, String teamId){
        Player[] players = new Player[5];
        for (int i: idList){
            players[i] = makePlayerFromId(i);
        }
        TeamFactory Factory = new CommonTeamFactory();
        return Factory.createTeam(teamId, List.of(players));
    }
}
