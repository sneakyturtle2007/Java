import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AutoGrader {
    public static String specific_assignment_or_test;
    public static int Amount_of_questions;
    public static int Questions_got_right;
    public static int question;
    public static int index;
    public static ArrayList<Integer> locations_of_answers = new ArrayList<Integer>();
    public static Scanner scanner1 = new Scanner(System.in);

    public AutoGrader() {
        specific_assignment_or_test = "nothing";
    }

    public AutoGrader(String initassignment_or_test) {
        specific_assignment_or_test = initassignment_or_test;
        System.out.println(specific_assignment_or_test);
    }

    public static void grader() {
        Amount_of_questions = 0;
        Questions_got_right = 0;
        locations_of_answers.clear();
        index = 0;
        String Answer;
        char[] answer;
        question = 0;
        while (index < specific_assignment_or_test.length()) {
            if (specific_assignment_or_test.charAt(index) == ':') {
                Amount_of_questions = Amount_of_questions + 1;
                locations_of_answers.add((index + 1));
            }
            index++;
        }
        while (question < Amount_of_questions) {
            System.out.print("Answer: ");
            if (scanner1.hasNextLine()) {
                Answer = scanner1.nextLine();
                // process the input here
            } else {
                scanner1.nextLine();
                Answer = scanner1.nextLine();
            }

            answer = Answer.toCharArray();
            int location_of_current_answer = locations_of_answers.get(question);
            if (specific_assignment_or_test.charAt(location_of_current_answer) == answer[0]) {
                Questions_got_right = Questions_got_right + 1;
            }
            question = question + 1;
        }
        if (Amount_of_questions > 0) {
            double results = (double) Questions_got_right / Amount_of_questions;
            System.out.println("--------------");
            System.out.println("grade: " + results * 100 + "%");
        }

    }

    public static ArrayList<String> Getassignmentsortests() throws IOException {
        ArrayList<String> Assignments_and_or_tests = new ArrayList<String>();
        FileReader tests_and_assignments = new FileReader("tests&assignments.txt");
        BufferedReader textreader = new BufferedReader(tests_and_assignments);
        String line;
        while ((line = textreader.readLine()) != null) {
            Assignments_and_or_tests.add(line);
        }
        textreader.close();
        tests_and_assignments.close();
        return Assignments_and_or_tests;
    }

    public static void start_program(ArrayList<String> Assignments_and_or_tests) {
        Scanner scanner1 = new Scanner(System.in);
        for (String assignmentortest : Assignments_and_or_tests) {
            System.out.println(assignmentortest);
        }
        if (Assignments_and_or_tests.size() != 0) {
            System.out.print("Enter the number of the assignment or test that you want to grade: ");
            int which_assignment_or_test = scanner1.nextInt();
            System.out.println(Assignments_and_or_tests.size());
            if (which_assignment_or_test <= Assignments_and_or_tests.size() - 1 && which_assignment_or_test >= 0) {
                /*
                 * specific_assignment is used to grab the specific line that the assignment
                 * answer key is in.
                 */
                String specific_assignment = Assignments_and_or_tests.get(which_assignment_or_test);
                AutoGrader test = new AutoGrader(specific_assignment);
                System.out.println("How many assignments would you like to grade: ");
                int response = scanner1.nextInt();

                for (int i = 0; i < response; i++) {
                    System.out.println("assignment: " + i + 1);
                    grader();

                }
            } else {
                System.out.println();
                System.out.println("That assignment or test does not exist");
            }

        } else {
            System.out.println("No assignments to grade.");
        }

    }

    public static void AssignmentorTestWriter(int amountoftestorassignments) throws IOException {
        String contentsoffilewithuserinput = "";
        int indexofnewassignmentortest = 0;
        for (String line : Getassignmentsortests()) {
            contentsoffilewithuserinput += line;
            contentsoffilewithuserinput += "\n";
            indexofnewassignmentortest++;
        }
        Scanner scanner03Scanner = new Scanner(System.in);
        for (int i = 0; i < amountoftestorassignments; i++) {

            System.out.println("Please input the name of the test/assignment: ");
            String newassignmentortest = scanner03Scanner.nextLine();
            System.out.println("Now please input the answer key of the assignment/test(1:a 2:b 3:c): ");
            String theassignmentortest = scanner03Scanner.nextLine();

            contentsoffilewithuserinput += (newassignmentortest + "(number " + indexofnewassignmentortest + ") - "
                    + theassignmentortest
                    + "\n");

            FileWriter assignmentortestrwriter = new FileWriter("tests&assignments.txt");
            assignmentortestrwriter.write(contentsoffilewithuserinput);
            assignmentortestrwriter.close();
        }

    }

    public static void main(String[] args) throws IOException {
        int gradeorwrite;
        int amountofanswerkeys;
        Scanner scanner01 = new Scanner(System.in);
        while (true) {

            System.out.print("Would you like to write add an assignment or grade one" + "\n" + "1 - Grade"
                    + "\n" + "2 - Add assignment" + "\n" + "3 - End program \n Enter input: ");
            gradeorwrite = Integer.parseInt(scanner01.next());
            System.out.println("--------------");

            if (gradeorwrite == 1) {
                start_program(Getassignmentsortests());
                System.out.println("--------------");
            } else if (gradeorwrite == 2) {
                System.out.println("How many would you like to add: ");
                amountofanswerkeys = Integer.parseInt(scanner01.next());

                AssignmentorTestWriter(amountofanswerkeys);
                System.out.println("--------------");
            } else {
                break;
            }

        }
        scanner01.close();
    }
}
