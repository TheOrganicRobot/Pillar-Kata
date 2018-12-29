package main.java.searchKata;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	
	public void Run() {
		FileRead file = new FileRead();
		GetWords words = new GetWords();
		MatrixBuilder matrix = new MatrixBuilder();
		Finder find = new Finder();
		Menu menu = new Menu();
		
		List<String[]> wordsAndMatrix = file.fileReader(menu.choice());
		
		List<String> wordsToSearchList = words.getWordsToSearch(wordsAndMatrix);
		menu.printOutWordsToSearch(wordsToSearchList);
		char[][] searchPuzzle = matrix.Matricize(wordsAndMatrix);
		menu.printOutWordSearchPuzzle(searchPuzzle);
		menu.searchAllDirections(wordsToSearchList, find, searchPuzzle);	
		playAgain();
	}
	
	public String choice() {
		Scanner scan = new Scanner(System.in);
		String filePath1 = "StarTrekSearch.csv"; //***COMMENT/UNCOMMENT TO TEST DIFFERENT FILES
		String filePath2 = "InstrumentSearch.csv"; //***COMMENT/UNCOMMENT TO TEST DIFFERENT FILES
		String filePath3 = "";
		String answer = "";
		
		while(true) {
		System.out.println("Which file would you like to test? (1, 2 or 3)\n");
		System.out.println("1. Star Trek Search?");
		System.out.println("2. Instrument Search?");
		System.out.println("3. Input your own puzzle?");
		answer = scan.next();
			if(!answer.equals("1") && !answer.equals("2") && !answer.equals("3")) {
				System.out.println("Sorry, that's not an option.\n");
			} else {
				break;
			}
		}
		if(answer.equals("1")) {
			return filePath1;
		} else if(answer.equals("2")) {
			return filePath2;
		} else if(answer.equals("3")) {
			System.out.println("\nPlease enter the path where your file is located:");
			scan.nextLine();
			answer = scan.nextLine();
			File fileToRead = new File(answer);
			
			if(!fileToRead.isFile() || fileToRead == null) {
				System.out.println("I'm sorry, you must have the wrong file location.\n\n");
				pause();
				Run();			
			} else if(fileToRead.isFile()) {
				filePath3 = answer;
				return filePath3;
			}
		}
		return null;
	}
	
	public void printOutWordsToSearch(List<String> wordsToSearchList) {
		System.out.println("\nHere are the words you're searching for:\n");
		pause();
		for(String words : wordsToSearchList) {
			System.out.print(words + " ");
		}
		System.out.println("\n\n");
		pause();
	}
	
	public void printOutWordSearchPuzzle(char[][] searchPuzzle) {
		System.out.println("Here is the puzzle you're searching through:");
		pause();
		for(int i = 0; i < searchPuzzle.length; i++) {
			System.out.println("");
			for(int j = 0; j < searchPuzzle[i].length; j++) {
				System.out.print(searchPuzzle[j][i] + " ");
			}
		}
		System.out.println("\n\n");
		pause();
	}
	
	public void searchAllDirections(List<String> wordsToSearchList, Finder find, char[][] searchPuzzle) {
		System.out.println("And here is where they are located:\n");
		pause();
		for(String word : wordsToSearchList) {
			find.searchWestToEast(searchPuzzle, word);
			find.searchEastToWest(searchPuzzle, word);
			find.searchNorthToSouth(searchPuzzle, word);
			find.searchSouthToNorth(searchPuzzle, word);
			find.searchNorthEastToSouthWest(searchPuzzle, word);
			find.searchNorthWestToSouthEast(searchPuzzle, word);
			find.searchSouthEastToNorthWest(searchPuzzle, word);
			find.searchSouthWestToNorthEast(searchPuzzle, word);
		}
		pause();
		System.out.println("\n\n");
	}
	
		
	private static void pause(){
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void playAgain() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to play again? (Y or N)");
		String answer = scan.next().toUpperCase();
		if(!answer.equals("Y") && !answer.equals("N")) {
			System.out.println("I'm sorry, that's not an option");
			playAgain();
		} else {
			if(answer.equals("Y")) {
				Run();
		} else if(answer.equals("N")) {
			System.out.println("Thanks for playing!!");
			System.exit(0);
		}	
		}
	}
}
