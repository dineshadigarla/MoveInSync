import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	private static int countMinCandies(int[] ratings) {
		int[] candies = new int[ratings.length];
		for (int i = 0; i < ratings.length; i++ ) {
			candies[i]=1;
		}
		for (int i = 0; i < candies.length;) {
			if (i > 0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) { 
				candies[i] = candies[i-1] + 1;
				i--;
			}
			
			else if (i < candies.length - 1 && ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) { 
				candies[i] = candies[i+1] + 1;
				if (i > 0) i--;
			} 
			
			else i++;
		}
		
		int totalCandies = 0;
		for (int c: candies) {
			totalCandies += c;
		}
		
		return totalCandies;
	}
	
	private static int[] readRatings() {
		try {
			Scanner scanner = new Scanner(System.in);
			
			int number = scanner.nextInt();
			
			if (number <= 0) throw new RuntimeException("Input: first line is a negative number");
			
			int[] ratings = new int[number];
			int i = 0;
			while (i < number) {
				int rating = scanner.nextInt();
				ratings[i] = rating;
				i++;
			}
		
			return ratings;
		} catch (NumberFormatException e) {
			throw new RuntimeException("Input corrupt: " + e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		int[] ratings = readRatings();
		int minCandies = countMinCandies(ratings);
		System.out.println(minCandies);
	}

}