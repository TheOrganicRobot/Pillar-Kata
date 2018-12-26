package searchTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import searchKata.FileRead;
import searchKata.Finder;
import searchKata.GetWords;
import searchKata.MatrixBuilder;

public class TestFile {
	
	
	private FileRead file;
	private String filePath;	
	private GetWords wordsToSearch;
	private MatrixBuilder matrix;
	private Finder find;
	
	private List<String[]> fileReadOutput;
	private List<String> myWords;
	private char[][] charMatrix;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
		
		
		filePath = "StarTrekSearch.csv";
		fileReadOutput = file.fileReader(filePath);
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput);
		charMatrix = matrix.Matricize(fileReadOutput);
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
		assertEquals(7, myWords.size());
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
	
	@Test 
	public void testSearchWestToEast() {
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);

		assertTrue(find.searchWestToEast(charMatrix, myWords.get(3)));
		assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", os.toString());		
	}
	
	@Test
	public void testSearchEastToWest() {
		
		assertTrue(find.searchEastToWest());
	}
	
	

}
