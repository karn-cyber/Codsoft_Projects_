@ -0,0 +1,95 @@
import java.util.*;

public class QuizApplication {
    // Question class to store questions, options, and correct answer
    static class Question {
        String questionText;
        String[] options;
        int correctAnswer;

        public Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    // Global variables for quiz
    private static final List<Question> questions = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static final int TIME_LIMIT = 10; // time limit in seconds

    // Populate questions for the quiz
    private static void initializeQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Rome", "4. Berlin"}, 1));
        questions.add(new Question("What is the square root of 64?", new String[]{"1. 6", "2. 7", "3. 8", "4. 9"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"}, 2));
        questions.add(new Question("Who developed the theory of relativity?", new String[]{"1. Newton", "2. Einstein", "3. Galileo", "4. Tesla"}, 2));
    }

    // Method to display question and manage timer
    private static boolean askQuestion(Question question) {
        System.out.println("\n" + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }
        
        // Timer setup
        Timer timer = new Timer();
        QuizTimerTask task = new QuizTimerTask();
        timer.schedule(task, TIME_LIMIT * 1000);

        System.out.print("Enter your answer (1-4): ");
        int answer = -1;

        // Check if answer is entered within time limit
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
            task.cancel(); // cancel timer if answered on time
        }
        
        timer.cancel(); // cancel timer if time runs out

        return answer == question.correctAnswer;
    }

    // Main method to run the quiz
    public static void main(String[] args) {
        initializeQuestions();
        System.out.println("Welcome to the Quiz! You have " + TIME_LIMIT + " seconds to answer each question.\n");

        for (Question question : questions) {
            boolean correct = askQuestion(question);
            if (correct) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Time's up or incorrect answer. Moving to the next question.");
            }
        }

        displayResult();
    }

    // Method to display final result
    private static void displayResult() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Summary of Answers:");
        
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.questionText);
            System.out.println("Correct Answer: " + q.options[q.correctAnswer - 1]);
        }
    }

    // Inner class to handle timing for each question
    static class QuizTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("\nTime's up! Moving to the next question.");
        }
    }
}
