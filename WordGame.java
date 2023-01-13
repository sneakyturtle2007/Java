import java.util.Random;
import java.util.Scanner;
public class WordGame{
    private static String secretWord;
    private static String currentStatus = "";
    public static String wordGuess;
    public WordGame(){
        Random rand = new Random();
        int rand_int = rand.nextInt(6);
        if(rand_int == 0){
            secretWord = "plane";
        }else if(rand_int == 1){
            secretWord = "boat";
        }else if(rand_int == 2){
            secretWord = "destroyed";
        }else if(rand_int == 3){
            secretWord = "planetarium";
        }else if(rand_int == 4){
            secretWord = "carbon";
        }
        setStatus();
    }
    private static void setStatus(){
            for(int i = 0; i < secretWord.length(); i++){
                currentStatus = currentStatus + "_";
            }
        }
    public static String getStatus(){
        return currentStatus;
    }
    public static boolean isDone(){
        if(currentStatus.indexOf("_") == -1){
            return true;
        }else{
            return false;
        }
    }
    public static boolean processGuess(String userGuess){
        int locationOfGuess = secretWord.indexOf(userGuess);
        if(locationOfGuess != -1){
            wordGuess = userGuess;
            for(int i = 0; i < currentStatus.length(); i++){
                if(i == secretWord.indexOf(userGuess)){
                    String beforeUserGuess = currentStatus.substring(0,i);
                    String afterUserGuess = currentStatus.substring(i + 1);
                    String beforeUserGuess1 = secretWord.substring(0,i);
                    String afterUserGuess1= secretWord.substring(i + 1);
                    currentStatus = beforeUserGuess + userGuess + afterUserGuess;
                    secretWord = beforeUserGuess1 +"_"+ afterUserGuess1;
                }
            }
            return true;
        }else{
            return false;
        } 
    }
    public static void main(String[] args){
        WordGame game = new WordGame();
        Scanner scan = new Scanner(System.in);
        int guessAmount = 0;
        System.out.println("Guess my secret word!" + "\nWord: " + WordGame.getStatus());
        while(!isDone()){
             System.out.print("Guess your letter: " );
            if(processGuess(scan.nextLine())){
                System.out.println("Great Guess!");
                System.out.println(getStatus());
                guessAmount ++;
            }else{
                System.out.println("Sorry, the letter " + wordGuess + " isn't in the word.");
                System.out.println(getStatus()); 
                guessAmount ++;
            }
        }
        scan.close();
        System.out.println("You guessed it in " + guessAmount+ " tries!"); 
    }
}