import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        System.out.println("\n========== HANGMAN GAME =========");
        System.out.println("Guess the hidden word one letter at a time before the hangman is fully drawn.\n");

        Scanner scanner = new Scanner(System.in);

        String continuePlaying = "";

        do{
            System.out.println("==== Play ====");

            String filePath = "src/hangman_words.txt";
            File wordsFile = new File(filePath);
            int wrongGuesses = 0;

            try(BufferedReader reader = new BufferedReader(new FileReader(wordsFile))){

                //Initialize array to hold all the words
                ArrayList<String> secretWords = new ArrayList<>();

                String line = reader.readLine(); // read first line

                //Add words to the secretWords array as long as there is a next line
                while (line != null){
                    secretWords.add(line);
                    line = reader.readLine();
                }

                //Generate a secretWord
                Random random = new Random();
                int secretWordIndex = random.nextInt(0, secretWords.size());
                String secretWord = secretWords.get(secretWordIndex).toUpperCase();
                //For testing
//               String secretWord = "APPLE";

                ArrayList<Character> wordState = new ArrayList<>();

                //Add dashes to wordState
                for(int i = 0; i< secretWord.length(); i++){
                    wordState.add('_');
                }

                //Display the initial word state for the user
                printWordState(wordState);

                //As long as the wordState contains dashes keep asking for guesses
                while(wordState.contains('_')){
                    //If user has guessed 6 or more times then break this loop
                    if(wrongGuesses >= 6){
                        System.out.println("❗GAME OVER❗");
                        System.out.printf("The word was %s%n", secretWord);
                        break;
                    }

                    //Ask user to guess a letter
                    System.out.print("\nGuess a letter: ");
                    char guessedCharacter = scanner.next().toUpperCase().charAt(0);



                    //Check if the guessed letter is in the secret word
                    if(secretWord.indexOf(guessedCharacter) != -1){
                        //If it is correct, loop through all the characters then replace the dashes with the correct character
                        for (int i=0; i < secretWord.length(); i++){
                            if (secretWord.charAt(i) == guessedCharacter){
                                wordState.set(i, guessedCharacter);

                            }

                        }

                        printWordState(wordState);
                        System.out.println("✅ CORRECT");

                    }else{ // character is not found
                        wrongGuesses ++;
                        printWordState(wordState);
                        System.out.println("❌ WRONG");
                        System.out.println(printHangMan(wrongGuesses));

                    }

                }

                if (!wordState.contains('_')){
                    System.out.println("🏆😎 YOU WIN");
                }









            } catch (FileNotFoundException e) {
                System.out.println("The word(s) file was not found! Add the hangman_words.txt file then proceed!");
                break;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file!");
                break;
            }


            System.out.print("\nContinue Playing? Yes/No: ");
            continuePlaying = scanner.next().toLowerCase();
        }while (continuePlaying.equals("yes") || continuePlaying.equals("y"));

        System.out.println("Bye!");

        scanner.close();

    }

    static void printWordState(ArrayList<Character> wordState){
        System.out.printf("Word: (%d letters): ", wordState.size());
        for (char c: wordState){
            System.out.print(c + " ");
        }
    }

    static String printHangMan(int wrongGuesses){
        return switch (wrongGuesses){
            case 1-> """
                      +---+
                      |   |
                      O   |
                          |
                          |
                          |
                    =========
                    """;
            case 2 -> """
                      +---+
                      |   |
                      O   |
                      |   |
                          |
                          |
                    =========
                    """;
            case 3 -> """
                      +---+
                      |   |
                      O   |
                     /|   |
                          |
                          |
                    =========
                    """;
            case 4 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                          |
                          |
                    =========
                    """;
            case 5 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     /    |
                          |
                    =========
                    """;
            case 6 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     / \\  |
                          |
                    =========
                    """;
            default -> "";
        };
    }

}