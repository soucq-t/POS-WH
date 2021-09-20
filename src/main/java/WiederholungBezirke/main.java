package WiederholungBezirke;

import java.awt.*;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) throws IOException {

        Set<Bezirk> allDataFromCSV=new TreeSet<Bezirk>(readCSV(Path.of("src/main/resources/WiederholungBezirke/bezirke_noe.csv")));
        allDataFromCSV.forEach(System.out::println);

        System.out.println("---------------------------");
        allDataFromCSV.stream().sorted(Comparator.comparing(Bezirk::getEinwohnerzahl)).forEach(System.out::println);

    }

    public static Collection<Bezirk> readCSV(Path path) throws IOException{
        try(Stream<String> lines = Files.lines(path)){
            TreeSet<Bezirk> allBezirks=new TreeSet<>();

            lines.forEach(s -> {
                String[] strs=s.split(",");
                try{
                    allBezirks.add(new Bezirk(Integer.parseInt(strs[0]),strs[1],Integer.parseInt(strs[2].replaceAll("\\.",""))));
                }catch (NumberFormatException e){
                    System.out.println(e);
                }
            });
            return allBezirks;
        }

    }
}
