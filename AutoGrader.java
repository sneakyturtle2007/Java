import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class AutoGrader {
    public static String specific_assignment_or_test;
    public static int Amount_of_questions;
    public static int Questions_got_right;
    public static int question;
    public static int index ;
    public static ArrayList<Integer> locations_of_answers = new ArrayList<Integer>();
    private static Scanner scanner1 = new Scanner(System.in); 
    public AutoGrader(){
        specific_assignment_or_test = "nothing";
    }
    public AutoGrader(String initassignment_or_test){
        specific_assignment_or_test = initassignment_or_test;
        System.out.println(specific_assignment_or_test);
    }
    public static void grader(){
        Amount_of_questions = 0;
        Questions_got_right = 0;
        locations_of_answers.clear();
        index = 0;
        String Answer;
        char[] answer;
        question = 0;
        while(index < specific_assignment_or_test.length()){
            if(specific_assignment_or_test.charAt(index) == ':'){
                Amount_of_questions  = Amount_of_questions + 1;
                locations_of_answers.add((index + 1));
            }
            index ++;
        }
        while(question<Amount_of_questions){
            System.out.print("Answer: ");
            Answer = scanner1.nextLine();
            answer = Answer.toCharArray();
            int location_of_current_answer = locations_of_answers.get(question);
            if(specific_assignment_or_test.charAt(location_of_current_answer)== answer[0]){
                Questions_got_right  = Questions_got_right + 1;
            }
            question  = question + 1;
        }
        double results = (double)Questions_got_right / Amount_of_questions;
        System.out.println("grade: " + results * 100 + "%");
    }
    public static void new_assignment_or_test(String initassignment_or_test){
        specific_assignment_or_test = initassignment_or_test;
    }
    public static void main(String[] args) {
        try {
            ArrayList<String> Assignments_and_or_tests = new ArrayList<String>();
            FileReader fr = new FileReader("tests&assignments.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Assignments_and_or_tests.add(line);
            }
            br.close();
            fr.close();
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Enter the assignment or test you want to grade: ");
            int which_assignment_or_test = scanner1.nextInt();
            String specific_assignment = Assignments_and_or_tests.get(which_assignment_or_test);
            AutoGrader test = new AutoGrader(specific_assignment);
            System.out.print("How many assignments would you like to grade: ");
            int response = scanner1.nextInt();
            int current_assignment= 1;
            for(int i = 0; i < response; i++){
                System.out.println("assignment: " + current_assignment);
                grader();
                current_assignment ++;
            }
            System.out.print("To turn off press the x at the top left.");
            int offzero = scanner1.nextInt();
            scanner1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
