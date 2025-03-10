// src/main/java/trivia/QuestionDeck.java
package trivia;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public class QuestionDeck {
    private final Map<String, LinkedList<String>> questions = new HashMap<>();

    public QuestionDeck() {
        questions.put("Pop", new LinkedList<>());
        questions.put("Science", new LinkedList<>());
        questions.put("Sports", new LinkedList<>());
        questions.put("Rock", new LinkedList<>());
        questions.put("Geography", new LinkedList<>());

        loadQuestions("Pop", "pop.properties");
        loadQuestions("Science", "science.properties");
        loadQuestions("Sports", "sports.properties");
        loadQuestions("Rock", "rock.properties");
        loadQuestions("Geography", "geography.properties");
    }

    private void loadQuestions(String category, String filename) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Decks/"+filename)) {
            if (input == null) {
                System.out.println("Could not find " + filename);
                return;
            }
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                questions.get(category).add(properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String askQuestion(String category) {
        return questions.get(category).removeFirst();
    }

    public Map<String, LinkedList<String>> getQuestions() {
        return questions;
    }
}