package entity;

public class UserScore {
    private String username;
    private String userId;
    private Float score;

    public UserScore(String username, String userId, float score) {
        this.username = username;
        this.userId = userId;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserScore{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", score=" + score +
                '}';
    }
}
