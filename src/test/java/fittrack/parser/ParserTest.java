package fittrack.parser;

import fittrack.user.User;
import fittrack.healthprofile.FoodWaterIntake;
import fittrack.trainingsession.TrainingSession;
import fittrack.reminder.Reminder;
import fittrack.fitnessgoal.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static fittrack.messages.Messages.HELP_MESSAGE;
import static fittrack.messages.Messages.INVALID_INPUT_MESSAGE;
import static fittrack.messages.Messages.INVALID_USER_INFO_MESSAGE;
import static fittrack.messages.Messages.SEPARATOR;
import static fittrack.parser.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ParserTest {

    private User user;
    private ArrayList<TrainingSession> sessionList;
    private ArrayList<Reminder> reminderList;
    private ArrayList<Goal> goalList;
    private FoodWaterIntake foodWaterList;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        user = new User("Male", "20");
        sessionList = new ArrayList<>();
        reminderList = new ArrayList<>();
        goalList = new ArrayList<>();
        foodWaterList = new FoodWaterIntake();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testHelpCommand() throws IOException {
        String input = "help";
        String expectedOutput = SEPARATOR + System.lineSeparator()
                + HELP_MESSAGE + System.lineSeparator()
                + SEPARATOR + System.lineSeparator() + System.lineSeparator();

        parse(user, input, sessionList, reminderList, goalList, foodWaterList);
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testSetUserCommandValidInput() throws IOException {
        String input = "set male 24";
        parse(user, input, sessionList, reminderList, goalList, foodWaterList);
        assertEquals(24, user.getAge());
        assertEquals("MALE", user.getGender().toString());
    }

    @Test
    void testSetUserCommandInvalidInput() throws IOException {
        String input = "set";
        String expectedOutput = INVALID_USER_INFO_MESSAGE;

        parse(user, input, sessionList, reminderList, goalList, foodWaterList);

        // Capture the actual output
        String actualOutput = outputStreamCaptor.toString().trim().replaceAll("\\r\\n?", "\n");

        // Normalize both the expected and actual output by removing extra line breaks or spaces
        expectedOutput = expectedOutput.trim().replaceAll("\\r\\n?", "\n");

        // Check console output is correct
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAddSessionCommandValidInput() {

    }

    @Test
    void testDeleteSessionCommandValidIndex() {

    }

    @Test
    void testDeleteSessionCommandInvalidIndex() {

    }

    @Test
    void testGraphPerformanceCommandValidInput() {

    }

    @Test
    void testListGoalCommandEmptyGoalList() {

    }

    @Test
    void testAddWaterCommand_ValidInput() {

    }

    @Test
    void testAddFoodCommandValidInput() {

    }

    @Test
    void testInvalidCommand() throws IOException {
        String input = "unknown-command";
        parse(user, input, sessionList, reminderList, goalList, foodWaterList);

        String expectedOutput = SEPARATOR + System.lineSeparator()
                + INVALID_INPUT_MESSAGE + System.lineSeparator() +
                SEPARATOR + System.lineSeparator();

        // Capture the actual output
        String actualOutput = outputStreamCaptor.toString().trim().replaceAll("\\r\\n?", "\n");

        // Normalize both the expected and actual output by removing extra line breaks or spaces
        expectedOutput = expectedOutput.trim().replaceAll("\\r\\n?", "\n");

        assertEquals(expectedOutput, actualOutput);
    }
}

