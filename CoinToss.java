import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class CoinToss {
	
	public static void main(String[] args) //heres where we be tossing them coins!
	{
		//Inital set up of random and scanner (for user input)
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the coin toss game!");
		System.out.println("In this game, coins are tossed, and you guess Heads or Tails. Now:");
		
		int numOfTosses = 0;
		boolean validNumber = false; //for exiting exception loop
		while (validNumber == false)
		{
			System.out.println("How many coins do you want to toss?");
			try
			{
				numOfTosses = scanner.nextInt();
				System.out.println("Alright! " + numOfTosses + " coins will be tossed!");
				validNumber = true;
			}
			catch (Exception e)
			{
				System.out.println("Thats not a valid number. Please try again");
				scanner.nextLine();
			}
		}
		
		scanner.nextLine();
			
		//fleshes out a list of coin tosses. The list of coin tosses is equal to what the user specified
		List<Boolean> coinTosses = new ArrayList<>();
		for(int i = 0; i < numOfTosses; i++)
		{
			boolean value = random.nextBoolean();
			coinTosses.add(value);
		}

		
		//List is used to track how many guess the player got correct
		List<Boolean> didPlayerGuessCorrect = new ArrayList<>();
		System.out.println("Now let the guessing begin!");
		for(int i = 0; i < numOfTosses; i++)
		{
			int guessTextNum = i+1; //exists purely for the player's message, telling them what guess he's on.
			System.out.println("Guess number " + guessTextNum);
			boolean acceptableGuess = false; //for exiting exception loop
			while(acceptableGuess == false)
			{
				System.out.println("Heads or Tails? (enter 'true' or 'false' to guess Heads or Tails)");
				try
				{	boolean playerGuess;
					playerGuess = scanner.nextBoolean();
					
					if(playerGuess == coinTosses.get(i))
					{
						System.out.println("CORRECT! Nice job!");
						didPlayerGuessCorrect.add(true);
					}
					else
					{
						System.out.println("Unfortunatly that is incorrect. Good try though!");
						didPlayerGuessCorrect.add(false);
						
					}
					
					scanner.nextLine(); //needed since many guesses will be made
					acceptableGuess = true; //sucessful guess, exit loop
				}
				catch (Exception e)
				{
					System.out.println("Thats not a valid guess. Please try again");
					scanner.nextLine();
				}
			}
		}
		
		int correctGuesses = 0;
		for(boolean guess: didPlayerGuessCorrect)
		{
			if(guess == true)
			{
				correctGuesses++;
			}
		}
		float successRatio = ((float) correctGuesses / numOfTosses) * 100;
		
		int numOfHeads = 0;
		int numOfTails = 0;
		for(boolean coin : coinTosses)
		{
			if(coin == true)
			{
				numOfHeads++;
			}
			else //assumes the alternative is tails
			{
				numOfTails++;
			}
		}
		
		
		System.out.println("Game over!");
		System.out.println("There were "+numOfHeads +" Heads and "+numOfTails+" Tails");
		System.out.println("You guessed correctly "+successRatio+"% of the time!");
		System.out.println("Hope you enjoyed!");
		scanner.close();
		

	}
}