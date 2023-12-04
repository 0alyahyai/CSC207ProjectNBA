package data_access;

import entity.*;
import use_case.algorithm.AlgorithmDataAccessInterface;
import use_case.compareTeam.CompareDataAccessInterface;
import use_case.entity_helpers.TeamComparator;
import use_case.leaderboard.LeaderboardFileUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.make_team.MakeTeamDAI;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.view_team.ViewTeamUserDataAccessInterface;

import java.io.*;
import java.util.*;


public class FileUserDataAccessObject implements
        SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        LeaderboardFileUserDataAccessInterface, MenuUserDataAccessInterface,
        MakeTeamDAI, ViewTeamUserDataAccessInterface, CompareDataAccessInterface, AlgorithmDataAccessInterface
{


    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private User activeUser;

    private UserFactory userFactory;

    private TeamFactory teamFactory = new CommonTeamFactory();

    private final APIinterface apiDAO;

    public FileUserDataAccessObject(
            String csvPath,
            UserFactory userFactory, APIinterface apiDAO) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        this.apiDAO = apiDAO;
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("user_id", 2);
        headers.put("team", 3);
        System.out.println(csvFile.length());

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,user_id,team");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String userid = String.valueOf((col[headers.get("user_id")]));

                    String userTeamString = String.valueOf((col[headers.get("team")]));
                    Team team = teamStringToTeam(userTeamString);

                    User user = userFactory.create(userid, username, password);
                    user.setTeam(team);
                    accounts.put(username, user);

                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String teamString;
                if (!user.hasTeam()) {
                    teamString = "NA";
                }
                else {
                    teamString = userTeamToString(user);
                }
                String line = String.format("%s,%s,%s,%s",
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getUserID(),
                        teamString
                );
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }


    /**
     * Return the active user.
     * @return the active user
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     * Return the active user.
     * @return the active userID
     */
    public String getActiveUserID() {
        if (activeUser == null) {
            return null;
        }
        return activeUser.getUserID();
    }

    /**
     * Set the active user.
     * @param user the user to set as active
     */
    @Override
    public void setActiveUser(User user) {
        activeUser = user;
    }

    public List<User> getUserswithTeam() {
        List<User> usersWithTeam = new ArrayList<>();
        for (User user : accounts.values()) {
            if (user.hasTeam()) {
                usersWithTeam.add(user);
            }
        }
        return usersWithTeam;
    }

    private List<UserScore> cachedOrderedUserScores;

    public void updateUserScores(TeamComparator teamComparator) {
        this.cachedOrderedUserScores = getOrderedUserScores(teamComparator);
    }


    public String[] getOrderedNames() {
        String[] result = new String[cachedOrderedUserScores.size()];

        for (int i = 0; i < cachedOrderedUserScores.size(); i++) {
            result[i] = cachedOrderedUserScores.get(i).getUsername();
        }
        return result;
    }

    public Float[] getOrderedScores() {
        Float[] result = new Float[cachedOrderedUserScores.size()];

        for (int i = 0; i < cachedOrderedUserScores.size(); i++) {
            result[i] = cachedOrderedUserScores.get(i).getScore();
        }
        return result;
    }

    public String[] getOrderedIDs() {
        String[] result = new String[cachedOrderedUserScores.size()];

        for (int i = 0; i < cachedOrderedUserScores.size(); i++) {
            result[i] = cachedOrderedUserScores.get(i).getUserId();
        }
        return result;
    }

    public List<UserScore> getOrderedUserScores(TeamComparator teamComparator) {
        List<User> usersWithTeam = this.getUserswithTeam();
        List<UserScore> userScores = new ArrayList<>();

        for (User user : usersWithTeam) {
            String userName = user.getUserName();
            Team userTeam = user.getUserTeam();
            Float score = teamComparator.getTeamScore(userTeam);
            UserScore userScore = new UserScore(userName, user.getUserID(), score);

            int i = 0;
            boolean added = false;

            while (i < userScores.size()) {
                if (userScores.get(i).getScore() < score) {
                    userScores.add(i, userScore);
                    added = true;
                    break;
                }
                i++;
            }

            if (!added) {
                userScores.add(userScore);
            }
        }
        return userScores;
    }

    @Override
    public boolean saveTeam(User user, Team team) {
        user.setTeam(team);
        this.save();
        return true;
    }

    @Override
    public Team getTeamOfUser(String userName) {
        User user = accounts.get(userName);
        return user.getUserTeam();
    }


    public static String userTeamToString(User user) {
        List<Player> players = user.getUserTeam().getTeamPlayers();
        String newTeamString = String.format("[%s;%s;%s;%s;%s;%s]",
                user.getUserTeam().getTeamName(),
                players.get(0).getPlayerID(),
                players.get(1).getPlayerID(),
                players.get(2).getPlayerID(),
                players.get(3).getPlayerID(),
                players.get(4).getPlayerID());
        return newTeamString;
    }



    public Team teamStringToTeam(String teamString) {
        if (teamString.equals("NA")) {
            return null;
        }
        PlayerFactory playerFactory = new CommonPlayerFactory();
        teamString = teamString.substring(1, teamString.length() -1); // removing square brackets
        String[] parts = teamString.split(";");
        String teamName = parts[0];

        int[] teamPlayerIds = new int[5];
        teamPlayerIds[0] = Integer.parseInt(parts[1]);
        teamPlayerIds[1] = Integer.parseInt(parts[2]);
        teamPlayerIds[2] = Integer.parseInt(parts[3]);
        teamPlayerIds[3] = Integer.parseInt(parts[4]);
        teamPlayerIds[4] = Integer.parseInt(parts[5]);

        Player p1 = playerFactory.create(
                apiDAO.getNameOfPlayer(teamPlayerIds[0]), teamPlayerIds[0]);
        Player p2 = playerFactory.create(
                apiDAO.getNameOfPlayer(teamPlayerIds[1]), teamPlayerIds[1]);
        Player p3 = playerFactory.create(
                apiDAO.getNameOfPlayer(teamPlayerIds[2]), teamPlayerIds[2]);
        Player p4 = playerFactory.create(
                apiDAO.getNameOfPlayer(teamPlayerIds[3]), teamPlayerIds[3]);
        Player p5 = playerFactory.create(
                apiDAO.getNameOfPlayer(teamPlayerIds[4]), teamPlayerIds[4]);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);

        Team team = teamFactory.createTeam(teamName, players);

        return team;

    }

    // VARP starts coding
    public Boolean activeUserhasTeam(){

        return getTeamOfUser(this.activeUser.getUserName()) != null;

    }




    @Override
    public List<Team> geteams(){
        List<String> usernames = this.getUsernamesExceptActiveUser();
        List<Team> teams = new ArrayList<>();
        for (String username : usernames){
            if (this.getTeamOfUser(username) != null) {
                teams.add(this.getTeamOfUser(username));
            }
        }
        return teams;

    }

    public List<String> getUsernamesExceptActiveUser() {
        List<String> usernames = new ArrayList<>();
        String activeUsername = (activeUser != null) ? activeUser.getUserName() : null;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String row;

            // Skip the header row
            reader.readLine();

            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");

                // First column is the username
                String username = col[headers.get("username")];

                // Add username to the list if it's not the active user's username
                if (!username.equals(activeUsername)) {
                    usernames.add(username);
                }
            }
        } catch (IOException e) {
            // In case, there is no file or something
            e.printStackTrace();
        }

        return usernames;
    }

    @Override
    public ArrayList<Team> getteams(String teamname) throws IOException{

        String userNameOne = findUserByTeam(teamname);
        Team otherTeam = this.getTeamOfUser(userNameOne);
        Team thisTeam = this.getTeamOfUser(activeUser.getUserName());
        ArrayList<Team> teamList = new ArrayList<>();
        teamList.add(thisTeam);
        teamList.add(otherTeam);
        return teamList;


    }

    @Override
    public String getActiveName() {
        return this.activeUser.getUserName();
    }


    public String findUserByTeam(String teamName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {
            String line;

            // Skip the header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                // Assuming the last column contains the array and first column contains the username. We substreact
                // first character given that it is a "["
                if (columns.length >= 4) {
                    String[] teamArray = columns[3].split(";");


                    if (teamArray.length > 0 && teamArray[0].substring(1).equals(teamName)) {

                        return columns[0]; // Return the username
                    }
                }
            }
        }
        return null; // Return null if no matching user is found


    }
    // VARP ends coding


    public static void main(String[] args) throws IOException {
        UserFactory uf = new CommonUserFactory();
//            FileUserDataAccessObject dao = new FileUserDataAccessObject("./users.csv",
//                    uf, new MockAPIDAO());
        FileUserDataAccessObject dao = new FileUserDataAccessObject("./users.csv",
                uf, new APIDataAccessObject());


//            dao.getteams();
        System.out.println(dao.getteams("victeam"));


    }




}
