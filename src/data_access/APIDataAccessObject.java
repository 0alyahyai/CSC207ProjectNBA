package data_access;

//import all packages required to make API calls
import java.lang.reflect.Array;
import java.util.*;
import java.io.IOException;
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
        return null;
    }

    //TODO: Decide on what should happen to this method. It is likely now obsolete, instead being replaced by the
    // getPlayerStats method.
    @Override
    public String[] viewTeamGetStats(Player player) {
        return new String[]{"1", "2", "3", "4", "5", "6", "07"};
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
