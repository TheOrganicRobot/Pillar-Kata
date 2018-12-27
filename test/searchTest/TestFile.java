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
	private OutputStream os;
	private PrintStream ps;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
			
		filePath = "StarTrekSearch.csv"; //----------- Test file location
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
	
	@Test 
	public void testSearchWestToEast() {
		assertTrue(find.searchWestToEast(charMatrix, myWords.get(3)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(0)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(1)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(2)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(4)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(5)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(6)));
		assertFalse(find.searchWestToEast(charMatrix, myWords.get(7)));
		assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", os.toString());		
	}
	
	@Test
	public void testSearchEastToWest() {
		assertTrue(find.searchEastToWest(charMatrix, myWords.get(2)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(0)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(1)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(3)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(4)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(5)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(6)));
		assertFalse(find.searchEastToWest(charMatrix, myWords.get(7)));
		assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)", os.toString());
	}
	
	@Test
	public void testSearchNorthToSouth() {
		assertTrue(find.searchNorthToSouth(charMatrix, myWords.get(0)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(1)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(2)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(3)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(4)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(5)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(6)));
		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(7)));
		assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)", os.toString());
	}
	
	@Test
	public void testSearchSouthToNorth() {
		assertTrue(find.searchSouthToNorth(charMatrix, myWords.get(1)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(0)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(2)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(3)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(4)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(5)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(6)));
		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(7)));
		assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)", os.toString());
	}

	@Test
	public void testSearchNorthWestToSouthEast() {
		assertTrue(find.searchNorthWestToSouthEast(charMatrix, myWords.get(4)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(0)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(1)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(2)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(3)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(5)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(6)));
		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(7)));
		assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)", os.toString());
	}
	
	@Test
	public void testSearchNorthEastToSouthWest() {
		
		assertTrue(find.searchNorthEastToSouthWest(charMatrix, myWords.get(6)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(0)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(1)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(2)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(3)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(4)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(5)));
		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(7)));	
		assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", os.toString());
	}
	
	@Test
	public void testSearchSouthWestToNorthEast() {
		assertTrue(find.searchSouthWestToNorthEast(charMatrix, myWords.get(7)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(0)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(1)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(2)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(3)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(4)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(5)));
		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(6)));
		assertEquals("CRUSHER: (5,14),(6,13),(7,12),(8,11),(9,10),(10,9),(11,8)", os.toString());
	}
}
