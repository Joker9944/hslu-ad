package online.vonarx.hslu.ad.cd;

import online.vonarx.hslu.ad.ResourceReader;

import java.io.IOException;
import java.util.List;

public class BuchstabensalatA {

    public static void main(String[] args) {
        final var numbers = getInput()
                .stream()
                .filter(s -> s.matches("\\d+"))
                .mapToInt(Integer::valueOf)
                .toArray();

        var sum = 0;
        for (var i = 0; i < numbers.length; i++) {
            sum += i * numbers[i];
        }

        System.out.println(sum);
    }

    public static List<String> getInput() {
        try {
            final var input = ResourceReader.readResourceAsString("/cd/Buchstabensalat.txt").split("\n");
            return List.of(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
