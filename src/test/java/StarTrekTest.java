package test.java;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.searchKata.FileRead;
import main.java.searchKata.Finder;
import main.java.searchKata.GetWords;
import main.java.searchKata.MatrixBuilder;



public class StarTrekTest {
	
	private FileRead file;
	private String filePath;	
	private GetWords wordsToSearch;
	private MatrixBuilder matrix;
	private Finder find;
	
	private List<String[]> fileReadOutput;
	private List<String> myWords;
	private char[][] charMatrix;
	private OutputStream os;
	private PrintStream ps;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
			
		filePath = "StarTrekSearch.csv";

		
		fileReadOutput = file.fileReader(filePath); //----------- Full list of all arrays and words from file
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput); //----------- List of words to be searched
		charMatrix = matrix.Matricize(fileReadOutput); //----------- Two dimensional array for searching words
		
		os = new ByteArrayOutputStream(); //----------- #1 of capturing system out stream for testing
		ps = new PrintStream(os); //----------- #2 of capturing system out stream for testing
		System.setOut(ps); //----------- #3 of capturing system out stream for testing
	}
	
	@Test
	public void testFileExists() {
		File fileToRead = new File(filePath);
		assertTrue(fileToRead.exists());
	}
	
	@Test
	public void testFileRead() {
		fileReadOutput = file.fileReader(filePath);
		assertEquals(16, fileReadOutput.size());	
	}
	
	@Test
	public void testGetWords() {		
		assertEquals(8, myWords.size());
	}

		@Test
		public void testCreateMatrixOfCharacters() {
			assertEquals(15, charMatrix.length);
			assertEquals('S', charMatrix[0][5]);
			assertEquals('C', charMatrix[1][5]);
			assertEquals('O', charMatrix[2][5]);
			assertEquals('T', charMatrix[3][5]);
			assertEquals('T', charMatrix[4][5]);
			assertEquals('Y', charMatrix[5][5]);
		}
}