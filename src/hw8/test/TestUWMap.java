package hw8.test;

import static org.junit.Assert.*;
import hw8.Node;
import hw8.UWMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class TestUWMap {
	UWMap uwMap;
	
	@Before
	public void setUp() throws Exception {
		uwMap = new UWMap();

	}
//=================================testAll buildings=====================================//
	@Test
	public void testAllbuildingsShortName() {
		Map<String,String> map = uwMap.allBuildings();
		assertTrue(map.containsKey("LOW"));
		assertTrue(map.containsKey("T65"));
		assertTrue(map.containsKey("UBS (Secret)"));
		assertTrue(map.containsKey("HUB (Food, W)"));

	}
	
	@Test
	public void testShortNameToFullName(){
		Map<String,String> map = uwMap.allBuildings();
		assertEquals("Loew Hall",map.get("LOW"));
		assertEquals("Thai 65",map.get("T65"));
		assertEquals("University Bookstore (Secret Entrance)",map.get("UBS (Secret)"));
		assertEquals("Student Union Building (West Food Entrance)",map.get("HUB (Food, W)"));
	}
	
//==============================testFindPath===============================================//
	@Test
	public void testShortMap(){
		List<Node> expected = new ArrayList<Node>();
		expected.add(new Node(2259.7112, 1715.5273));
		expected.add(new Node(2260.7214, 1707.4461));
		expected.add(new Node(2217.7899, 1695.5768));
		expected.add(new Node(2156.585, 1675.3697));
		expected.add(new Node(2159.9587,1694.8192));
		List<Node> path = uwMap.findPath("CSE", "EEB");
		assertTrue(path.size()==expected.size());
		for(int i=0;i<path.size();i++){
			assertTrue(expected.get(i).equals(path.get(i)));
		}
	}
//==============================testGetDirection==========================================//
	@Test
	public void testWest(){
		Node start = new Node(1.0, 1.0);
		Node end = new Node(0.0,1.0);
		assertEquals("W",uwMap.getDirection(start, end));
	}
	@Test
	public void testEast(){
		Node end = new Node(1.0, 1.0);
		Node start = new Node(0.0,1.0);
		assertEquals("E",uwMap.getDirection(start, end));
	}
	@Test
	public void testNorth(){
		Node start = new Node(1.0, 1.0);
		Node end = new Node(1.0,0.0);
		assertEquals("N",uwMap.getDirection(start, end));
	}
	@Test
	public void testSouth(){
		Node end = new Node(1.0, 1.0);
		Node start = new Node(1.0,0.0);
		assertEquals("S",uwMap.getDirection(start, end));
	}
	@Test
	public void testSW(){
			Node start = new Node(1.0, 1.0);
			Node end = new Node(0.0,2.0);
			assertEquals("SW",uwMap.getDirection(start, end));
	}
	@Test
	public void testNE(){
		Node end = new Node(1.0, 1.0);
		Node start = new Node(0.0,2.0);
		assertEquals("NE",uwMap.getDirection(start, end));
	}
	@Test
	public void testSE(){
		Node start = new Node(1.0, 1.0);
		Node end = new Node(3.0,3.0);
		assertEquals("SE",uwMap.getDirection(start, end));
	}
	@Test
	public void testNW(){
		Node end = new Node(1.0, 1.0);
		Node start = new Node(3.0,3.0);
		assertEquals("NW",uwMap.getDirection(start, end));
	}
}
