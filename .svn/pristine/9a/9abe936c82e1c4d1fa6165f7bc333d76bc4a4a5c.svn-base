package hw8.test;

import static org.junit.Assert.*;
import hw8.Node;
import org.junit.Test;

public class TestNode {

	@Test
	public void testNodexAndy() {
		Node node = new Node(1.1,2.2);
		assertTrue(node.x==1.1);
		assertTrue(node.y==2.2);
	}
	
	@Test
	public void testEquals(){
		Node node1 = new Node(1.1,2.2);
		Node node2 = new Node(1.1,2.2);
		assertTrue(node1.equals(node2));
	}
	@Test
	public void testHashCode(){
		Node node = new Node(1.1,2.2);
		assertEquals(51,node.hashCode());
	}
}
