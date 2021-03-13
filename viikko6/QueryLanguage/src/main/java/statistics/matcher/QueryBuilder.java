package statistics.matcher;

public class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder () {

    }

    public Matcher build() {
        return matcher;
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new PlaysIn(team);
        return this;
    }
}
