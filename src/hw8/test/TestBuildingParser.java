package hw8.test;

import static org.junit.Assert.*;
import hw8.BuildingParser;
import hw8.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class TestBuildingParser {
	Map<String,String> shortToFull;
	Map<String,Node> coordinate;
	@Before
	public void setUp() throws Exception {
		shortToFull = new TreeMap<String,String>();
		coordinate = new HashMap<String,Node>();
		String filename = "src/hw8/data/campus_buildings.dat";
		BuildingParser.parseData(filename, shortToFull, coordinate);
		
	}

	@Test
	public void testContainsBuiliding() {
		assertTrue(shortToFull.containsKey("CSE"));
		assertTrue(shortToFull.containsKey("MUS"));
		assertTrue(shortToFull.containsKey("MGH"));
		assertTrue(shortToFull.containsKey("RAI"));
	}
	
	@Test
	public void testShortNametoFullName(){
		assertEquals("Miller Hall",shortToFull.get("MLR"));
		assertEquals("Moore Hall",shortToFull.get("MOR"));
		assertEquals("Suzzallo Library",shortToFull.get("SUZ"));
		assertEquals("Thai 65",shortToFull.get("T65"));
		assertEquals("Chemistry Library (Southeast Entrance)",shortToFull.get("CHL (SE)"));
		assertEquals("Odegaard Undergraduate Library",shortToFull.get("OUG"));
		assertEquals("Mary Gates Hall (Southwest Entrance)",shortToFull.get("MGH (SW)"));
	}
	
	@Test
	public void testShortNameToCoordinate(){
		Node node = new Node(1874.338,1212.4713);
		assertEquals(node, coordinate.get("KNE (E)"));
		node = new Node(2243.4795,1040.5275);
		assertEquals(node, coordinate.get("MUS (SW)"));
		node = new Node(1895.8038,1325.861);
		assertEquals(node, coordinate.get("SUZ"));
		node = new Node(2309.4107,1979.0003);
		assertEquals(node, coordinate.get("ROB"));
	}

}
