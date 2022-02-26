package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.common.PhoenixException;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class PersonRepository {
    private static final String FILE_NAME = "./phoenix-showcase/persons.txt";

    public List<String> findAll() {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new PhoenixException(e);
        }
    }

    public void save(List<String> persons) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(FILE_NAME))) {
            wr.write("");

            for (String person : persons) {
                wr.append(person).append("\n");
            }
        } catch (IOException e) {
            throw new PhoenixException(e);
        }
    }
}
