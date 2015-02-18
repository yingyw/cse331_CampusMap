package hw8.test;

import static org.junit.Assert.*;
import hw8.Node;
import hw8.PathsPaser;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestPathsPaser {
	String filename;
	Map<Node,Map<Node,Double>> map;
	@Before
	public void setUp() throws Exception {
		filename = "src/hw8/data/campus_paths.dat";
		map = new HashMap<Node,Map<Node,Double>>();
		PathsPaser.parseData(filename, map);
	}

	@Test
	public void testContainsPosition() {
		Node node = new Node(2337.0143,806.8278);
		assertTrue(map.containsKey(node));
		node = new Node(2063.0526,2009.5072);
		assertTrue(map.containsKey(node));
		node = new Node(1945.7143,1036.5);
		assertTrue(map.containsKey(node));
		node = new Node(2301.7239,1296.7118);
		assertTrue(map.containsKey(node));
		node = new Node(2302.2652,1316.7409);
		assertTrue(map.containsKey(node));
	}
	
	@Test
	public void testCostofPath(){
		Node start = new Node(1989.2857,459.71429);
		Node end = new Node(1981.2581,472.81156);
		assertTrue(33.23802199792749-map.get(start).get(end)==0 );
		assertTrue(33.23802199792749-map.get(end).get(start)==0 );
		start = new Node(3530.049,1272.8905);
		end = new Node(3563.3513,1259.8944);
		assertTrue(77.80093455742848-map.get(start).get(end)==0);
		assertTrue(77.80093455742848-map.get(end).get(start)==0);
	}
	
	@Test
	public void testPaths(){
		Node start = new Node(2283.5714,516.14286);
		Node end1 = new Node(2272.1429,527.92857);
		Node end2 = new Node(2343.2142,542.92857);
		Node end3 = new Node(2345.7143,528.64286);
		Node end4 = new Node(2303.2143,508.64286);
		assertTrue(map.get(start).containsKey(end1));
		assertTrue(map.get(start).containsKey(end2));
		assertTrue(map.get(start).containsKey(end3));
		assertTrue(map.get(start).containsKey(end4));
	}

}
