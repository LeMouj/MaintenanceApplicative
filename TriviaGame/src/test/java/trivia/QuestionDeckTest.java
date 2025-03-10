package trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDeckTest {

    private QuestionDeck questionDeck;

    @BeforeEach
    public void setUp() {
        questionDeck = new QuestionDeck();
    }

    @Test
    public void test_01_LoadQuestions() {
        Map<String, LinkedList<String>> questions = questionDeck.getQuestions();
        assertFalse(questions.get("Pop").isEmpty());
        assertFalse(questions.get("Science").isEmpty());
        assertFalse(questions.get("Sports").isEmpty());
        assertFalse(questions.get("Rock").isEmpty());
        assertFalse(questions.get("Geography").isEmpty());
    }

    @Test
    public void test_02_AskQuestion() {
        String question = questionDeck.askQuestion("Pop");
        assertNotNull(question);
        assertFalse(question.isEmpty());
    }

    @Test
    public void test_03_AskAllQuestions() {
        Map<String, LinkedList<String>> questions = questionDeck.getQuestions();
        for (String category : questions.keySet()) {
            while (!questions.get(category).isEmpty()) {
                String question = questionDeck.askQuestion(category);
                assertNotNull(question);
                assertFalse(question.isEmpty());
            }
        }
    }

    @Test
    public void test_04_AskQuestionFromEmptyDeck() {
        Map<String, LinkedList<String>> questions = questionDeck.getQuestions();
        questions.get("Pop").clear();
        assertThrows(NoSuchElementException.class, () -> questionDeck.askQuestion("Pop"));
    }

    @Test
    public void test_05_LoadQuestionsFileNotFound() {
        QuestionDeck faultyDeck = new QuestionDeck() {
            private void loadQuestions(String category, String filename) {
                // Do nothing to prevent loading default questions
            }
        };
        try {
            Method method = QuestionDeck.class.getDeclaredMethod("loadQuestions", String.class, String.class);
            method.setAccessible(true);
            method.invoke(faultyDeck, "Pop", "nonexistent.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse(faultyDeck.getQuestions().get("Pop").isEmpty());
    }
}