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
		List<String[]> FileReadOutput = file.fileReader(filePath);
		assertEquals(16, FileReadOutput.size());
		
	}
	@Test
	public void testGetWords() {
		
		GetWords wordsToSearch = new GetWords();
		String[] myWords = wordsToSearch.wordsToSearch();
		
		assertEquals(0, myWords.length);
	}

}
