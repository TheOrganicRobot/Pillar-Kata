package searchTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import searchKata.FileRead;
import searchKata.GetWords;
import searchKata.MatrixBuilder;

public class TestFile {
	
	private String filePath;
	private FileRead file;
	private List<String[]> fileReadOutput;
	
	@Before
	public void setup() {
		filePath = "StarTrekSearch.csv";
		file = new FileRead();
		fileReadOutput = file.fileReader(filePath);
	}
	
	@Test
	public void testFileExists() {
		File fileToRead = new File(filePath);
		assertTrue(fileToRead.exists());
	}
	
	@Test
	public void testFileRead() {
		assertEquals(16, fileReadOutput.size());	
	}
	
	@Test
	public void testGetWords() {	
		GetWords wordsToSearch = new GetWords();
		List<String> myWords = wordsToSearch.getWordsToSearch(fileReadOutput);	
		assertEquals(7, myWords.size());
	}
	
	@Test
	public void testCreateMatrixOfCharacters() {
		MatrixBuilder matrix = new MatrixBuilder();
		GetWords wordsToSearch = new GetWords();
		List<String> myWords = wordsToSearch.getWordsToSearch(fileReadOutput);
		char[][] charMatrix = matrix.Matricize();
		assertEquals(0, charMatrix.length);
	}

}
