package use_case.entity_helpers;
import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import data_access.APIinterface;
import entity.Player;
import entity.Stats;
import use_case.algorithm.AlgorithmDataAccessInterface;

import java.util.ArrayList;

public class MarkovPlayerEvaluator implements PlayerEvaluator{
    public APIinterface apIinterface; //these are parameters

    public MarkovPlayerEvaluator(AlgorithmDataAccessInterface algorithmDataAccessInterface,APIinterface apIinterface){
        this.apIinterface = apIinterface;

    }

    @Override
    public float evaluatePlayer(Player player) {
        this.apIinterface.getPlayerStats(player.getPlayerID());


        ArrayList<Double> arrayOfStat1;
        ArrayList<Double> arrayOfStat2;
        ArrayList<Double> arrayOfStat3;
        //TODO VICTOR HERE WE GET THE VALUES WANTED (SOME LISTS) TO PLUG IN AFTER AND PONDERAR.HACEMOS HELPER PARA LAS TRES.
//        return 1/3*(MarkovPlayerEvaluator.MarkovApproximation(arrayOfStat1)+MarkovPlayerEvaluator.MarkovApproximation(arrayOfStat2)+MarkovPlayerEvaluator.MarkovApproximation(arrayOfStat3));
        return 0F;

    }

    public static Double MarkovApproximation(ArrayList<Double> arrayOfStats){
        //The stats are arranged chronologically. That makes everything easier.
        Double meanX = MarkovPlayerEvaluator.getMean(arrayOfStats);
        Double sigmaX = MarkovPlayerEvaluator.getSigma(arrayOfStats, meanX);
        Double[][] markovMatrixNotRounded = MarkovPlayerEvaluator.getMarkovProbabilities(arrayOfStats, meanX, sigmaX);
        Double[][] markovMatrix = MarkovPlayerEvaluator.makeStochastic(markovMatrixNotRounded);



        //
        //
        //
        //   zone-3         zone-2        zone-1        zone 1      zone 2        zone 3
        //
        //   -----(-)3sX/2---------(-)sX/2---------meanX---------sX/2---------3sX/2-----
        //
        //
        //where are we. Where is the player in the last mach:
        Double[] vector = MarkovPlayerEvaluator.findStationaryDistribution(markovMatrix);
        int index = MarkovPlayerEvaluator.getRandomIndex(vector); //Number from 0 to 5


            switch(index) {
                case 0:
                    return meanX - 3/2*sigmaX;
                case 1:
                    return meanX - 1*sigmaX;
                case 2:
                    return meanX - 1/4*sigmaX;
                case 3:
                    return meanX + 1/4*sigmaX;
                case 4:
                    return meanX + 1*sigmaX;
                case 5:
                    return meanX + 3/2*sigmaX;
                default:
                    return meanX;


        }








    }
    public static int getRandomIndex(Double[] probabilities) {
        double randomValue = Math.random(); // Generate a random number between 0.0 and 1.0
        double sum = 0;

        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
            if (randomValue <= sum) {
                return i; // Return the index as soon as the accumulated sum exceeds the random value
            }
        }

        return probabilities.length - 1; // In case of rounding errors, return the last index
    }

    public static Double[] findStationaryDistribution(Double[][] matrix) {
        int size = matrix.length;
        RealMatrix coefficients = new Array2DRowRealMatrix(size, size);

        // Subtract the identity matrix from the transition matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double value = matrix[j][i] != null ? matrix[j][i] : 0.0;
                coefficients.setEntry(i, j, value - (i == j ? 1.0 : 0.0));
            }
        }

        // Replace the last row with ones for the sum-to-one constraint
        for (int i = 0; i < size; i++) {
            coefficients.setEntry(size - 1, i, 1.0);
        }

        // Create a constant vector with the last entry as 1 (sum-to-one constraint)
        double[] constants = new double[size];
        constants[size - 1] = 1.0;

        // Solve the linear system
        DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(new Array2DRowRealMatrix(constants).getColumnVector(0));

        // Convert the solution to Double[]
        Double[] stationaryDistribution = new Double[size];
        for (int i = 0; i < size; i++) {
            stationaryDistribution[i] = solution.getEntry(i);
        }

        return stationaryDistribution;
    }

    public static Double[][] getMarkovProbabilities(ArrayList<Double> arrayOfStats, Double meanX, Double sigmaX ){
        Double[][] MarkovProbabilities = new Double[6][6];
        for (int i = 0; i < MarkovProbabilities.length; i++) {
            for (int j = 0; j < MarkovProbabilities[i].length; j++) {
                MarkovProbabilities[i][j] = 0.0; // Initialize with zero
            }
        }

        for(int i = 0; i < 6; i++){
            Double[] column = new Double[6];
            //In this case, i is the node where we are
            for(int j = 0; j < 6; j++){
                Double pseudoentry = (double) 0;
                //Only compute from here to the others. Markov chains

                    //How may times from i to j
                    for (int z = 0; z < arrayOfStats.size() - 1; z++){
                        if (MarkovPlayerEvaluator.whatInterval(arrayOfStats.get(z), meanX, sigmaX) - 1 == i
                                && MarkovPlayerEvaluator.whatInterval(arrayOfStats.get(z + 1), meanX, sigmaX) - 1 == j){
                            pseudoentry = pseudoentry + (double)0;
                        }
                    }


                column[j] = pseudoentry;
            }
             //We have have a pseudo entry
            for(int j = 0; j < 6; j++){
                //Only compute from here to the others
                MarkovProbabilities[i][j] = 0.5 + (0.70*column[j]/MarkovPlayerEvaluator.getSum(column));



            }



        }
        return MarkovProbabilities;
    }

    public static Double[][] makeStochastic(Double[][] matrix) {
        for (Double[] row : matrix) {
            double sum = 0;
            // Calculate the sum of the row
            for (Double val : row) {
                sum += val;
            }

            // Adjust each value in the row
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i] / sum;
            }
        }
        return matrix;
    }

    public static int whatInterval(Double z, Double meanX, Double sigmaX){
        if(z <= meanX-3*sigmaX/2){
            return 1;
        } else if (meanX-3*sigmaX/2 < z && z <= meanX-1*sigmaX/2) {
            return 2;
        } else if (meanX-1*sigmaX/2 < z && z <= meanX-0*sigmaX/2) {
            return 3;
        } else if (meanX-0*sigmaX/2 < z && z <= meanX+1*sigmaX/2) {
            return 4;
        } else if (meanX-0*sigmaX/2 < z && z <= meanX+1*sigmaX/2) {
            return 5;
        } return 6;

    }
    public static Double getSum(Double[] column){
        Double total = (double) 0;
        for(int i = 0; i < column.length; i++){
            total = total + column[i];
        }
        return total;

    }

    public static Double getMean(ArrayList<Double> arrayOfStats){
        Double meanX = (double) 0;
        for(int i = 0; i < arrayOfStats.size(); i++){
            meanX = meanX + arrayOfStats.get(i);
        }
        return meanX;
    }
    public static Double getSigma(ArrayList<Double> arrayOfStats, Double meanX){
        Double sigmaX = (double) 0;
        for(int i = 0; i < arrayOfStats.size(); i++){
            sigmaX = (sigmaX + arrayOfStats.get(i)) * (sigmaX + arrayOfStats.get(i));
        }
        return sigmaX/arrayOfStats.size();
    }

}
