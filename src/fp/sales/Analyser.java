package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Analyser {

    private Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {
        // kasutasin GPT abi
        return repository.stream.map(Entry::getAmount).mapToDouble(each -> each).sum();
    }

    public Double getSalesByCategory(String category) {
        return repository.stream.filter(entry -> entry.getCategory().equals(category))
                .mapToDouble(Entry::getAmount).sum();

    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.stream.filter(entry -> entry.getDate().isAfter(start) && entry.getDate().isBefore(end)).mapToDouble(Entry::getAmount).sum();

    }

    public String mostExpensiveItems() {
        return repository.stream.sorted(Collections.reverseOrder(Comparator.comparingDouble(Entry::getAmount)))
                .limit(3).map(Entry::getProductId)
                .sorted().collect(Collectors.joining(", "));
    }

    public String statesWithBiggestSales() {
        return repository.stream.collect(Collectors.groupingBy(Entry::getState, Collectors.summingDouble(Entry::getAmount)))
                .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(3)
                .map(Map.Entry::getKey).collect(Collectors.joining(", "));
    }

}


