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
	private GetWords wordsToSearch;
	private List<String> myWords;
	
	@Before
	public void setup() {		
		file = new FileRead();
		wordsToSearch = new GetWords();
		filePath = "StarTrekSearch.csv";
		fileReadOutput = file.fileReader(filePath);
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput);
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
		assertEquals(7, myWords.size());
	}
	
	@Test
	public void testCreateMatrixOfCharacters() {
		MatrixBuilder matrix = new MatrixBuilder();
		char[][] charMatrix = matrix.Matricize(fileReadOutput);
		assertEquals(15, charMatrix.length);
		assertEquals('S', charMatrix[0][5]);
		assertEquals('C', charMatrix[1][5]);
		assertEquals('O', charMatrix[2][5]);
		assertEquals('T', charMatrix[3][5]);
		assertEquals('T', charMatrix[4][5]);
		assertEquals('Y', charMatrix[5][5]);
	}

}
