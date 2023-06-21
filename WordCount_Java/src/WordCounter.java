import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Raditya Dito\\Documents\\Universitas Indonesia\\Semester 4\\Sistem Basis Data\\Hadop\\Java Code\\WordCount\\WordCount\\src\\10mb.txt";
        int iterations = 5;
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;

                // Create a word count map
                Map<String, Integer> wordCountMap = new HashMap<>();

                // Read the file line by line
                while ((line = reader.readLine()) != null) {
                    // Clean the text by removing non-alphanumeric characters and converting to lowercase
                    String cleanedText = cleanText(line);

                    // Split the line into words
                    String[] words = splitIntoWords(cleanedText);

                    // Update the word count map
                    updateWordCount(words, wordCountMap);
                }

                // Display the word count results
                displayWordCount(wordCountMap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            totalTime += executionTime;

            System.out.println("Iteration " + (i + 1) + " Execution Time: " + executionTime + " milliseconds");
        }

        double averageTime = (double) totalTime / iterations;
        System.out.println("Average Execution Time: " + averageTime + " milliseconds");
    }

    private static String cleanText(String text) {
        return text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
    }

    private static String[] splitIntoWords(String text) {
        return text.split(" ");
    }

    private static void updateWordCount(String[] words, Map<String, Integer> wordCountMap) {
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
    }

    private static void displayWordCount(Map<String, Integer> wordCountMap) {
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            // System.out.println(word + ": " + count);
        }
    }
}
