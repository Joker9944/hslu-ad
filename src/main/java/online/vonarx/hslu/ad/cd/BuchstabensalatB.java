package online.vonarx.hslu.ad.cd;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuchstabensalatB {

    public static void main(String[] args) {
        final var inputSplit = BuchstabensalatA.getInput()
                .stream()
                .collect(Collectors.partitioningBy(s -> s.matches("\\d+")));
        final var numbers = inputSplit.get(true).stream()
                .mapToInt(Integer::valueOf)
                .toArray();
        final var letters = inputSplit.get(false);

        final var lettersIndexed = IntStream.range(0, Math.min(numbers.length, letters.size()))
                .mapToObj(i -> new Tuple<>(numbers[i], letters.get(i)))
                .toList();

        final var stringRaw = new ArrayList<String>();
        lettersIndexed.forEach(tuple -> stringRaw.add(tuple.a, tuple.b));

        System.out.println(String.join("", stringRaw));
        // 247187
    }

    public record Tuple<X, Y>(X a, Y b) {
    }
}
