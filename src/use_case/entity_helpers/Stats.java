package use_case.entity_helpers;

/**
 * A "boundary" allowing the PlayerEvaluator entity to communicate with the use-case layer so that it can access
 * the API interface.
 */
public interface Stats {


    public void getStats(String[] stats);

}
