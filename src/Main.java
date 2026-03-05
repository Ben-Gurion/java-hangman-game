import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        //Get the secret word
        String secretWord = "Banana";
        secretWord = secretWord.toUpperCase();
        int wrongGuessCount = 0;

        ArrayList<Character> wordState = new ArrayList<>();

        //Display dashes
        for (int i = 0; i < secretWord.length() ; i++) {
            wordState.add('_');
        }

        System.out.printf("Word (%d Letters): %s", secretWord.length(), wordState);

        while (wordState.contains('_')){
            // Check if we've reached the max number of guesses

            if (wrongGuessCount >= 6){
                //Game over
                System.out.println("❗GAME OVER❗");
                System.out.printf("The word was %s%n", secretWord);
                break;
            }else{

                //Ask the user to guess a letter

                System.out.print("\nGuess a letter: ");


                char guessedCharacter = scanner.next().toUpperCase().charAt(0);

                //check if the word contains the guessed character

                if (secretWord.indexOf(guessedCharacter) != -1){
                    // if it does, loop through the word and replace all instances of the character

                    for (int i = 0; i < secretWord.length(); i++) {
                        if(secretWord.charAt(i) == guessedCharacter){
                            wordState.set(i, guessedCharacter);
                        }

                    }

                    System.out.println("✅ CORRECT");
                    System.out.printf("Word: %s", wordState);

                }else{
                    wrongGuessCount ++;
                    System.out.println("❌ WRONG");

                    // Only display the hangman if there is a wrong guess
                    System.out.println(printHangMan(wrongGuessCount));
                }

            }

        }

        if(!wordState.contains('_')){
            System.out.println("\n🏆😎 YOU WIN");
        }


        scanner.close();
    }

    static String printHangMan(int wrongGuessCount){
        return switch(wrongGuessCount){
            case 0 -> "";
            case 1 -> """
                    🥺
                    """;
            case 2 -> """
                    🥺
                    ||
                    """;
            case 3 -> """
                    🥺
                   /||
                   """;
            case 4 -> """
                    🥺
                   /||\\
                   """;
            case 5 -> """
                    🥺
                   /||\\
                    /
                   """;
            case 6 -> """
                    🥺
                   /||\\
                    /\\
                   """;
            default -> "";

        };
    }

}
