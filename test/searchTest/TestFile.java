package searchTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import searchKata.FileRead;
import searchKata.GetWords;

public class TestFile {

	
	@Test
	public void testFileRead() {
		
		String filePath = "StarTrekSearch.csv";
		FileRead file = new FileRead();
		List<String[]> fileReadOutput = file.fileReader(filePath);
		assertEquals(16, fileReadOutput.size());
		
	}
	@Test
	public void testGetWords() {
		
		GetWords wordsToSearch = new GetWords();
		String filePath = "StarTrekSearch.csv";
		FileRead file = new FileRead();
		List<String[]> fileReadOutput = file.fileReader(filePath);
		List<String> myWords = wordsToSearch.getWordsToSearch(fileReadOutput);
		
		assertEquals(7, myWords.size());
	}

}
