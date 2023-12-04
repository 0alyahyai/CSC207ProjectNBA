package use_case.entity_helpers;

import data_access.APIinterface;
import entity.Player;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogarithmicPlayerEvaluator implements PlayerEvaluator{

    public APIinterface apIinterface; //these are parameters

    public LogarithmicPlayerEvaluator(APIinterface apIinterface){
        this.apIinterface = apIinterface;

    }
    @Override
    public float evaluatePlayer(Player player) {
        Map<String, Object> dataMap = this.apIinterface.getPlayerStats(player.getPlayerID());
        if (isResponseEmpty(dataMap)){
            return 1;
        }
        ArrayList<Double> pointsArray = LogarithmicPlayerEvaluator.extractPoints(dataMap);
        ArrayList<Double> reboundsArray = LogarithmicPlayerEvaluator.extractTotalRebounds(dataMap);
        ArrayList<Double> assistsArray = LogarithmicPlayerEvaluator.extractAssists(dataMap);
        Double Points = LogarithmicPlayerEvaluator.LogarithmicPlayerEvaluation(pointsArray);
        Double Rebounds = LogarithmicPlayerEvaluator.LogarithmicPlayerEvaluation(reboundsArray);
        Double Assists = LogarithmicPlayerEvaluator.LogarithmicPlayerEvaluation(assistsArray);
        return (float) ((Points + Rebounds + Assists)/3);



    }

    public static boolean isResponseEmpty(Map<String, Object> dataMap) {
        if (dataMap == null || !dataMap.containsKey("response")) {
            return true; // The map is null or doesn't contain the key "response"
        }

        Object response = dataMap.get("response");
        if (response instanceof List) {
            return ((List<?>) response).isEmpty();
        }

        return true; // The response is not a List or is empty
    }
    public static Double LogarithmicPlayerEvaluation(ArrayList<Double> arrayOfStats){
        int sizeOfArray = arrayOfStats.size();
        // Here we want to ponderar logarithmically. Cuanfo ponderas quieres que todos sumen 1
        // 20, 19, 18, 17, 16..., 3, 2, 1
        ArrayList<Double> dataWithExponents = LogarithmicPlayerEvaluator.listforGeometricMean(arrayOfStats);
        Double GeometricMeanX =LogarithmicPlayerEvaluator.Geometric(dataWithExponents);
        return (double)GeometricMeanX;
    }
    public static Double Geometric(ArrayList<Double> array){
        if (array.isEmpty()){
            return (double)0;
        }
        Double multiplication = (double)1;
        for(int i = 0; i < array.size(); i++){
            multiplication = multiplication * array.get(i);
        }
        Double GeometricMeanX = Math.pow(multiplication, (double) 1/array.size());
        return GeometricMeanX;
    }
    public static ArrayList<Double> listforGeometricMean(ArrayList<Double> arrayOfStats){
        int sizeOfArray = arrayOfStats.size();
        ArrayList<Double> newArray = new ArrayList<>();
        if (arrayOfStats.isEmpty()){
            newArray.add((double)0);
            return newArray;
        }
        for(int i = 0; i < sizeOfArray; i++){
            newArray.add(Math.pow((arrayOfStats.get(i)), (double) (i + 1) /sizeOfArray));
        }
        return newArray;
    }


    public static ArrayList<Double> extractAssists(Map<String, Object> dataMap) {
        ArrayList<Double> assistsList = new ArrayList<>();

        if (dataMap == null || !dataMap.containsKey("response") || !(dataMap.get("response") instanceof List)) {
            return assistsList; // Returns an empty list if the map is empty or does not contain the key "response"
        }

        List<?> responseList = (List<?>) dataMap.get("response");

        for (Object gameData : responseList) {
            if (gameData instanceof Map) {
                Map<?, ?> gameMap = (Map<?, ?>) gameData;
                if (gameMap.containsKey("assists") && gameMap.get("assists") instanceof Number) {
                    assistsList.add(((Number) gameMap.get("assists")).doubleValue());
                }
            }
        }

        return assistsList;
    }


    public static ArrayList<Double> extractPoints(Map<String, Object> dataMap) {
        ArrayList<Double> pointsList = new ArrayList<>();

        if (dataMap == null || !dataMap.containsKey("response") || !(dataMap.get("response") instanceof List)) {
            return pointsList; // Returns an empty list if the map is empty or does not contain the key "response"
        }

        List<?> responseList = (List<?>) dataMap.get("response");

        for (Object gameData : responseList) {
            if (gameData instanceof Map) {
                Map<?, ?> gameMap = (Map<?, ?>) gameData;
                if (gameMap.containsKey("points") && gameMap.get("points") instanceof Number) {
                    pointsList.add(((Number) gameMap.get("points")).doubleValue());
                }
            }
        }

        return pointsList;
    }

    public static ArrayList<Double> extractTotalRebounds(Map<String, Object> dataMap) {
        ArrayList<Double> reboundsList = new ArrayList<>();

        if (dataMap == null || !dataMap.containsKey("response") || !(dataMap.get("response") instanceof List)) {
            return reboundsList; // Returns an empty list if the map is empty or does not contain the key "response"
        }

        List<?> responseList = (List<?>) dataMap.get("response");

        for (Object gameData : responseList) {
            if (gameData instanceof Map) {
                Map<?, ?> gameMap = (Map<?, ?>) gameData;
                if (gameMap.containsKey("totReb") && gameMap.get("totReb") instanceof Number) {
                    reboundsList.add(((Number) gameMap.get("totReb")).doubleValue());
                }
            }
        }

        return reboundsList;
    }



}
