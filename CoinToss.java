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
		String coinHeadFace = "Heads";
		String coinTailFace = "Tails";
		
		System.out.println("Welcome to the coin toss game!");
		System.out.println("In this game, coins are tossed, and you guess Heads or Tails-");
		
		boolean acceptableAnswer = false;
		while(acceptableAnswer == false)
		{
			System.out.println("Would you like to make your own custom coin faces?");
			System.out.println("(enter true for yes, false for no)");
			try
			{	
				boolean answer = scanner.nextBoolean();
				
				if(answer == true) //the editing of the faces is handled here
				{
					scanner.nextLine();
					System.out.println("ooooh a special little snowflake, ok.");
					System.out.println("What do you want on the first face instead of heads?");

						boolean finishedHeads = false;
						while(!finishedHeads)
						{
							String attemptedHeads = scanner.nextLine();
							if(attemptedHeads.length() > 10)
							{
								System.out.println("Try to keep it within 10 characters");
							}
							else if (attemptedHeads.matches(".*\\d.*"))
							{
								System.out.println("Please try to avoid numbers");
							}
							else
							{
								coinHeadFace = attemptedHeads;
								finishedHeads = true; 
							}
						}
						System.out.println("What do you want on the second face instead of tails?");
						boolean finishedTails = false;
						while(!finishedTails)
						{
							String attemptedTails = scanner.nextLine();
							if(attemptedTails.length() > 10)
							{
								System.out.println("Try to keep it within 10 characters");
							}
							else if (attemptedTails.matches(".*\\d.*"))
							{
								System.out.println("Please try to avoid numbers");
							}
							else
							{
								coinTailFace = attemptedTails;
								finishedTails = true; 
							}
						}
						System.out.println("Alright! Your custom coin now is "+coinHeadFace+" or "+coinTailFace);
						acceptableAnswer = true;
						//scanner.nextLine();
				}
				else
				{
					System.out.println("You must like Vanilla Icecream. Anyway:");
					acceptableAnswer = true;
					scanner.nextLine();
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Thats not a valid input");
				scanner.nextLine();
			}
		}	
		
		
		int numOfTosses = 0;
		int selectedGamemode = 0; //determined outside because this is used to skip over certain chunks of code
		boolean validGamemode = false;
		while(!validGamemode)
		{
			try 
			{
				System.out.println("Enter what gamemode you would you like to play");
				System.out.println("1 for Normal");
				System.out.println("2 for Streak (game ends when you guess wrong)");
				selectedGamemode = scanner.nextInt();
				if(selectedGamemode == 1)
				{
					validGamemode = true;
					System.out.println("Normal Mode selected");
					scanner.nextLine();
				}
				else if (selectedGamemode == 2)
				{
					validGamemode = true;
					System.out.println("Streak Mode selected");
					scanner.nextLine();
				}
				else
				{
					System.out.println("Invalid Gamemode");
					scanner.nextLine();
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid Gamemode");
				scanner.nextLine();
			}
		}
		
		if(selectedGamemode == 1) //All of the original gameplay code is here, since it was normal mode
		{
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
			

			int correctGuessStreak = 0; //the current streak of correct guesses
			int highestStreak = 0; //the highest streak of guesses the player has
			
			//List is used to track how many guess the player got correct
			List<Boolean> didPlayerGuessCorrect = new ArrayList<>();
			System.out.println("Now let the guessing begin!");
			
			for(int i = 0; i < numOfTosses; i++) //Loop that handles the guessing (by using function)
			{
				boolean guessResult = CoinFlip(coinHeadFace, coinTailFace, coinTosses.get(i), scanner);
				if(guessResult) 
				{
					correctGuessStreak++;
					didPlayerGuessCorrect.add(true);	
				}
				else
				{
					if(correctGuessStreak > highestStreak)
					{
						highestStreak = correctGuessStreak;
					}
					correctGuessStreak = 0;
					didPlayerGuessCorrect.add(false);
				}

			}
			
			//run one last time after the loop in case the player got ALL of them correct
			if(correctGuessStreak > highestStreak)
			{
				highestStreak = correctGuessStreak;
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
			System.out.println("There were "+numOfHeads+" "+coinHeadFace+" and "+numOfTails+" "+coinTailFace);
			System.out.println("You guessed correctly "+successRatio+"% of the time!");
			System.out.println("Your highest correct guess streak was "+highestStreak+" in a row!");
			System.out.println("Hope you enjoyed!");
			scanner.close();
		}	
		
		else if(selectedGamemode == 2)
		{
			boolean brokeStreak = false;
			int streak = 0;
			System.out.println("Now let the guessing begin!");
			while(!brokeStreak)
			{
				boolean headsOrTails = random.nextBoolean();
				boolean coinTossResult = CoinFlip(coinHeadFace, coinTailFace, headsOrTails, scanner);
				
				if(coinTossResult)
				{
					System.out.println("Keep going!");
					streak++;
				}
				else
				{
					System.out.println("oh...unfortunate");
					brokeStreak = true;
				}
			}
			
			System.out.println("Game over!");
			System.out.println("You managed to get a streak of "+streak);
			System.out.println("Thanks for playing!");
		}
		else
		{
			System.out.println("This Else shouldn't be reachable");
		}
	}	
	
	//head or tail is whether the coin in question is Heads (true) or Tails (false)
	public static boolean CoinFlip(String heads, String tails, Boolean headOrTail, Scanner scanner) 
	{
		boolean acceptableGuess = false;
		while(acceptableGuess == false)
		{
			System.out.println(heads + " or " +tails+ "? (enter 'true' or 'false' to guess)");
			try
			{	boolean playerGuess;
				boolean returnedValue; //Returns whether the player got it correct or not
				playerGuess = scanner.nextBoolean();
				
				if(playerGuess == headOrTail)
				{
					System.out.println("CORRECT! Nice job!");
					returnedValue = true;
				}
				else
				{
					System.out.println("Unfortunatly that is incorrect. Good try though!");
					//set the highest streak to the current streak if current streak is higher
					returnedValue = false;
				}
				
				scanner.nextLine(); //needed since many guesses will be made
				acceptableGuess = true; //sucessful guess, exit loop
				return returnedValue;
			}
			catch (Exception e)
			{
				System.out.println("Thats not a valid guess. Please try again");
				scanner.nextLine();
			}
		}
		System.out.println("This message should not appear");
		return false; //this shouldn't be reachable, but is required to stop java from freaking out
	}
}
