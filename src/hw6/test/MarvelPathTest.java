//Yingying Wang
//CSE331 HW6
//SECTION AB
package hw6.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import hw5.Graph;
import hw6.MarvelPaths;

import org.junit.Before;
import org.junit.Test;

public class MarvelPathTest {

	private static Graph<String,String> graphTest;
	
	@Before
	public void setUp(){
		graphTest = new Graph<String, String>();
	}
	/**TestThrowException==========================================================================================*/
	@Test(expected = IllegalArgumentException.class)
	public void testPassIntoAStartCharNotInsideGraph(){
		graphTest.addNode("B");
		MarvelPaths.findPath(graphTest,"A","B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassIntoAEndCharNotInsideGraph(){
		graphTest.addNode("A");
		MarvelPaths.findPath(graphTest,"A","B");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAllPassedCharsNotInsideGraph(){
		MarvelPaths.findPath(graphTest,"A","B");
	}
	
	/**TestFindShortestPath==========================================================================================*/
	@Test
	public void testFindShortestPath(){
		graphTest.addNode("A");
		graphTest.addNode("B");
		graphTest.addNode("C");
		graphTest.addNode("D");
		graphTest.addNode("E");
		graphTest.addNode("F");
		graphTest.addNode("G");
		graphTest.addNode("H");
		graphTest.addEdge("A", "B", "1");
		graphTest.addEdge("B", "C", "2");
		graphTest.addEdge("C", "D", "3");
		graphTest.addEdge("D", "E", "4");
		graphTest.addEdge("E", "F", "5");
		graphTest.addEdge("F", "G", "6");
		graphTest.addEdge("G", "H", "7");
		graphTest.addEdge("A", "D", "PATH1");
		graphTest.addEdge("D", "H", "PATH2");
		List<String> actual = new ArrayList<String>();
		actual.add("A");
		actual.add("D");
		actual.add("H");
		assertEquals(actual,MarvelPaths.findPath(graphTest, "A", "H"));
	}
	
	@Test
	public void testNoPathBetweenTwoGivenChar(){
		graphTest.addNode("A");
		graphTest.addNode("B");
		assertEquals(null, MarvelPaths.findPath(graphTest, "A", "B"));
	}
	
	@Test
	public void testAlphabeticallyLeastPathLabel(){
		graphTest.addNode("A");
		graphTest.addNode("B");
		graphTest.addEdge("A", "B", "a");
		graphTest.addEdge("A", "B", "b");
		graphTest.addEdge("A", "B", "c");
		graphTest.addEdge("A", "B", "d");
		graphTest.addEdge("A", "B", "e");
		List<String> nodes = MarvelPaths.findPath(graphTest, "A", "B");		
		TreeSet<String> labels = new TreeSet<String>();
		for(String label:graphTest.getAllLabels(nodes.get(0), nodes.get(1))){
			labels.add(label);
		}
		String actual = labels.first();
		String expected = "a";
		assertEquals(expected,actual);
	}
	
	@Test
	public void testAlphabeticallyLeastPathNode(){
		graphTest.addNode("A");
		graphTest.addNode("B");
		graphTest.addNode("C");
		graphTest.addNode("D");
		graphTest.addNode("E");
		graphTest.addNode("F");
		graphTest.addEdge("A", "B", "e1");
		graphTest.addEdge("B", "C", "e2");
		graphTest.addEdge("C", "D", "e3");
		graphTest.addEdge("D", "E", "e4");
		graphTest.addEdge("E", "F", "e5");
		graphTest.addEdge("A", "D", "e6");
		graphTest.addEdge("A", "D", "e7");
		graphTest.addEdge("D", "F", "e8");
		graphTest.addEdge("B", "F", "e9");
		List<String> expectedNode = new ArrayList<String>();
		expectedNode.add("A");
		expectedNode.add("B");
		expectedNode.add("F");
		List<String> actualNodes = MarvelPaths.findPath(graphTest, "A", "F");	
		assertEquals(expectedNode,actualNodes);
	}
	/**TestBuildGraph==========================================================================================*/
	@Test
	public void testBuildGraph(){
		graphTest = MarvelPaths.loadGraph("src/hw6/data/implementTest.tsv");
		Set<String> allNodes = graphTest.getAllNodes();
		Set<String> actual = new TreeSet<String>();
		for(String s: allNodes){
			actual.add(s);
		}
		Set<String> expected = new TreeSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("D");
		assertEquals(expected,actual);
	}

}
