package entity;

/**
 * A "boundary" allowing the PlayerEvaluator entity to communicate with the use-case layer so that it can access
 * the API interface.
 */
public interface Stats {


    public void setStats(String[] stats);

}
