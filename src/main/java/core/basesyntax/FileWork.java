package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileWork {
    public String[] readFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        File file = new File(fileName);

        List<String> text = null;

        try {
            text = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Problem with file", e);
        }

        List<String> listOfWords = findWordsStartsWithW(text, result);

        return removePunctual(listOfWords);
    }

    private List<String> findWordsStartsWithW(List<String> text, List<String> result) {
        if (!text.isEmpty()) {
            for (String line : text) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.startsWith("w") || word.startsWith("W")) {
                        result.add(word.toLowerCase());
                    }
                }
            }
        }
        return result;
    }

    private String[] removePunctual(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            String cleanedWord = result.get(i).replaceAll("\\p{Punct}+$", "");
            result.set(i, cleanedWord);
        }
        return Arrays.stream(result.toArray(new String[0])).sorted().toArray(String[]::new);
    }
}
