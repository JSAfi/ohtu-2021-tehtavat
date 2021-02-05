package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

/*  Jätetään toistaiseksi kommentteihin jos tarvitsee palata
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText ); */

        Gson mapper = new Gson();
        List<Player> players = Arrays.asList(mapper.fromJson(bodyText, Player[].class));

        List<Player> sortedPlayers = players.stream()
                                            .filter(nat -> "FIN".equals(nat.getNationality()))
                                            .sorted(Comparator.comparingInt(Player::getPoints).reversed())
                                            .collect(Collectors.toList());
        sortedPlayers.forEach(System.out::println);
    }

}
