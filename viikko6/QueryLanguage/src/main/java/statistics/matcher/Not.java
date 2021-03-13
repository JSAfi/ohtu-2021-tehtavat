package statistics.matcher;

import statistics.Player;

import java.lang.reflect.Method;

public class Not implements Matcher {
    private Matcher condition;

    public Not(Matcher condition) {
        this.condition = condition;
    }

    @Override
    public boolean matches(Player p) {
        if (this.condition.matches(p)) {
            return false;
        }
        return true;
    }
}
