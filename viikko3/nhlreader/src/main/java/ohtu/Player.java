
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int games;
    private int assists;
    private int goals;
    private int points;

    public void setName(String name) {
        this.name = name;
    }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setTeam(String team) { this.team = team; }
    public void setGames(int games) { this.games = games; }
    public void setAssists(int assists) { this.assists = assists; this.points = this.assists + this.points; }
    public void setGoals(int goals) { this.goals = goals; this.points = this.assists + this.points; }

    public String getName() {
        return name;
    }
    public String getNationality() { return nationality; }
    public String getTeam() { return this.team; }
    public int getGames() { return this.games; }
    public int getAssists() { return this.assists; }
    public int getGoals() { return this.goals; }
    public int getPoints() { return this.points; }

    @Override
    public String toString() {
        return name + " from " + nationality + " playing for " + team + ", " + goals + " + " + assists + " in " + games + " matches.";
    }
      
}
