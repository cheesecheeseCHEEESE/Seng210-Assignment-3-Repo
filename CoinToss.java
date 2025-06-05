import java.util.Random;
import java.util.Scanner;


public class CoinToss {
	
	public static void main(String[] args) //heres where we be tossing them coins!
	{
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		boolean coin = random.nextBoolean(); //True is Heads, False is Tails (gurantees only 2 outcomes)
		
		//existing for testing purposes
		//System.out.println(coin);
		
		boolean acceptableGuess = false; //for exiting exception loop
		while(acceptableGuess == false)
		{
			System.out.println("Heads or Tails? (enter 'true' or 'false' to guess Heads or Tails)");
			try
			{	boolean playerGuess;
				playerGuess = scanner.nextBoolean();
				
				if(playerGuess == coin)
				{
					System.out.println("CORRECT! Nice job!");
				}
				else
				{
					System.out.println("Unfortunatly that is incorrect. Good try though");
				}
				
				acceptableGuess = true; //sucessful guess, exit loop
				scanner.close();
			}
			catch (Exception e)
			{
				System.out.println("Thats not a valid guess. Please try again");
				scanner.nextLine();
			}
		}
		
		
		
	}
}