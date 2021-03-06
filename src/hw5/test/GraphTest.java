package hw5.test;

import static org.junit.Assert.*;
import hw5.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * Yingying Wang
 * 1127423
 * Section AB
 */

public class GraphTest {
	private static Graph<String, String> graphTest;
	
	@Before
	public void addNodes(){
		graphTest = new Graph<String, String>();
	}
/*TestConstructor=============================================================================*/	
	@Test
	public void testConstructor() {
		Graph<String, String> g = new Graph<String,String>();
		assertEquals(g.getAllNodes().size(),0);
	}
/*TestAddNode=============================================================================*/	
	@Test
	public void Size0Initial() {
		assertEquals("no node added to graph", 0, graphTest.getAllNodes().size());
	}
	
	@Test
	public void Size1AfterAddOnly1Node() {
		graphTest.addNode("n1");
		assertEquals("A node n1 added to graph", 1, graphTest.getAllNodes().size());
	}
	
	@Test
	public void Size1AfterAddSameNode() {
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		assertEquals("A node n1 added three times to graph", 1, graphTest.getAllNodes().size());
	}
	@Test
	public void Size4AfterAddMultipleNode(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addNode("n3");
		assertEquals("three nodes added to graph", 3, graphTest.getAllNodes().size());
	}
	@Test
	public void ContiansN1AfterAddit(){
		graphTest.addNode("n1");
		Set<String> expected = new HashSet<String>();
		expected = graphTest.getAllNodes();
		assertEquals(expected,graphTest.getAllNodes());
	}
	
	@Test
	public void AddReaptedNode(){
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		graphTest.addNode("n1");
		Set<String> expected = new HashSet<String>();
		expected = graphTest.getAllNodes();
		assertEquals(expected,graphTest.getAllNodes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassNullintoAddNode(){
		graphTest.addNode(null);
	}
	/*TestAddEdge=============================================================================*/	
	@Test
	public void noEdgeAdded(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		assertFalse(graphTest.checkEdge("n1", "n2"));
	}
	@Test
	public void add1EdgeBetweenN1andN2(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "label1");
		assertTrue(graphTest.checkEdge("n1", "n2"));
	}
	@Test
	public void addEdgeBetweenSameNode2(){
		graphTest.addNode("n1");
		graphTest.addEdge("n1", "n1", "label1");
		graphTest.addEdge("n1", "n1", "label2");
		assertTrue(graphTest.checkEdge("n1", "n1"));
		assertTrue(graphTest.getAllLabels("n1", "n1").contains("label1"));
		assertTrue(graphTest.getAllLabels("n1", "n1").contains("label2"));
	}
	@Test
	public void add1EdgeBetweenN1andN2checkEdge(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "label");
		Set<String> expected= new HashSet<String>();
		expected.add("label");
		assertEquals(expected, graphTest.getAllLabels("n1", "n2"));
	}
	@Test
	public void testAddMultipleEdgesFromN1toN2(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "label1");
		graphTest.addEdge("n1", "n2", "label2");
		graphTest.addEdge("n1", "n2", "label3");
		graphTest.addEdge("n1", "n2", "label4");
		Set<String> expected= new HashSet<String>();
		expected.add("label1");
		expected.add("label2");
		expected.add("label3");
		expected.add("label4");
		assertEquals(expected, graphTest.getAllLabels("n1", "n2"));
	}
	@Test
	public void testAddRepeatedEdgesFromN1toN2(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "label1");
		graphTest.addEdge("n1", "n2", "label1");
		graphTest.addEdge("n1", "n2", "label1");
		graphTest.addEdge("n1", "n2", "label1");
		ArrayList<String> expected= new ArrayList<String>();
		expected.add("label1");
		ArrayList<String> actural= new ArrayList<String>();
		for(String s: graphTest.getAllLabels("n1", "n2")){
			actural.add(s);
		}
		assertEquals(expected, actural);
	}
	@Test(expected=IllegalArgumentException.class)
	public void DoNotHasGivenNodeinGraph(){
		graphTest.addEdge("n1", "n2", "x");
	}
	@Test(expected=IllegalArgumentException.class)
	public void PassNullintoAddEdge(){
		graphTest.addEdge(null, null, null);
	}
	
	/*TestRemoveNode=============================================================================*/	
	@Test(expected = IllegalArgumentException.class)
	public void testPassnullintoRemoveNode(){
		graphTest.removeNode(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testPassedNodeThatGraphDoesNotContain(){
		graphTest.removeNode("n1");
	}
	@Test
	public void removeOneNode(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.removeNode("n1");
		assertFalse(graphTest.containsNode("n1"));
	}
	@Test
	public void removeMoreNode(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addNode("n3");
		graphTest.addNode("n4");
		graphTest.addNode("n5");
		graphTest.removeNode("n1");
		graphTest.removeNode("n2");
		graphTest.removeNode("n3");
		graphTest.removeNode("n4");
		graphTest.removeNode("n5");
		assertFalse(graphTest.containsNode("n1") && graphTest.containsNode("n2") &&
				graphTest.containsNode("n3") && graphTest.containsNode("n4") &&
				graphTest.containsNode("n5"));
	}
	@Test
	public void removeEndPointsAfterRemoveNode(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "label");
		graphTest.removeNode("n2");
		assertFalse(graphTest.checkEdge("n1", "n2"));
	}
	
	/*TestRemoveEdge=============================================================================*/	
	@Test(expected = IllegalArgumentException.class)
	public void testPassnullintoRemoveEdge(){
		graphTest.removeEdge(null, null, null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testGraphDoesNotContainGivenNode(){
		graphTest.removeEdge("n1", "n2", "label");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoGivenLabelBetweenGivenNodes(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.removeEdge("n1", "n2", "x");
	}
	@Test
	public void testRemoveSingleNodeAndNoOtherLabels(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "x");
		graphTest.removeEdge("n1", "n2", "x");
		assertFalse(graphTest.getAllEnds("n1").contains("n2"));
	}
	@Test
	public void testRemoveSingleEdge(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "x");
		graphTest.addEdge("n1", "n2", "y");
		graphTest.removeEdge("n1", "n2", "x");
		assertFalse(graphTest.getAllLabels("n1", "n2").contains("x"));
	}
	
	@Test
	public void testRemoveMoreEdges(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "x");
		graphTest.addEdge("n1", "n2", "y");
		graphTest.addEdge("n1", "n2", "a");
		graphTest.addEdge("n1", "n2", "b");
		graphTest.addEdge("n1", "n2", "c");
		graphTest.addEdge("n1", "n2", "d");

		graphTest.removeEdge("n1", "n2", "x");
		graphTest.removeEdge("n1", "n2", "y");
		graphTest.removeEdge("n1", "n2", "d");
		assertFalse(graphTest.getAllLabels("n1", "n2").contains("x"));
		assertFalse(graphTest.getAllLabels("n1", "n2").contains("y"));
		assertFalse(graphTest.getAllLabels("n1", "n2").contains("d"));
	}
	/*TestCheckEdge=============================================================================*/	
	@Test(expected = IllegalArgumentException.class)
	public void testPassNullintoCheckEdge(){
		graphTest.checkEdge(null, null);
	}
	@Test
	public void testCheckEdge(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addEdge("n1", "n2", "x");
		assertTrue(graphTest.checkEdge("n1", "n2"));
	}
	@Test
	public void testCheckEdgeFail(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		assertFalse(graphTest.checkEdge("n1", "n2"));
	}
	
	@Test
	public void testIfNodeNotContainedInGraph(){
		assertFalse(graphTest.checkEdge("n1", "n2"));
	}
	
	/*TestGetAllEnds=============================================================================*/	
	@Test(expected = IllegalArgumentException.class)
	public void testPassNullintogetAllEnds(){
		graphTest.getAllEnds(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testPassedNodeDoesNotInGraph(){
		graphTest.getAllEnds("n1");
	}
	@Test
	public void testCheckgetAllEnds(){
		graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addNode("n3");
		graphTest.addNode("n4");
		graphTest.addNode("n5");
		graphTest.addEdge("n1", "n2", "x");
		graphTest.addEdge("n1", "n3", "y");
		graphTest.addEdge("n1", "n4", "a");
		graphTest.addEdge("n1", "n5", "b");
		Set<String> expected = new HashSet<String>();
		expected.add("n2");
		expected.add("n3");
		expected.add("n4");
		expected.add("n5");
		assertEquals(expected, graphTest.getAllEnds("n1"));
	}
	@Test
	public void testCheckgetAllEndsWhenNoEnds(){
		graphTest.addNode("n1");
		assertEquals(0, graphTest.getAllEnds("n1").size());
	}
	/*TestGetAllNodes=============================================================================*/	
   @Test
	public void testGetAllNodeWhenEmpty(){
	   assertEquals(0,graphTest.getAllNodes().size());
   }
   @Test
  	public void testGetAllNodeWhenAddedMultipleNode(){
	    graphTest.addNode("n1");
		graphTest.addNode("n2");
		graphTest.addNode("n3");
		graphTest.addNode("n4");
		graphTest.addNode("n5");
		Set<String> expected = new HashSet<String>();
		expected.add("n1");
		expected.add("n2");
		expected.add("n3");
		expected.add("n4");
		expected.add("n5");
		assertEquals(expected,graphTest.getAllNodes());
     }
	/*TestEdgeNumbers=============================================================================*/	
   @Test(expected=IllegalArgumentException.class)
  	public void nodePassedIntoIsNull(){
  	   graphTest.EdgeNumbers(null, null);
     }
   @Test(expected=IllegalArgumentException.class)
 	public void nodePassedIntoDoesNotInGraph(){
 	   graphTest.EdgeNumbers("n1", "n2");
    }
   @Test
   public void onlyOneEdge(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   graphTest.addEdge("n1", "n2", "x");
	   assertEquals(1, graphTest.EdgeNumbers("n1", "n2"));

   }
   
   @Test
   public void onlyMoreEdge(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   graphTest.addEdge("n1", "n2", "x");
	   graphTest.addEdge("n1", "n2", "y");
	   graphTest.addEdge("n1", "n2", "a");
	   assertEquals(3, graphTest.EdgeNumbers("n1", "n2"));

   }
   @Test
   public void addedSameLabelofEdge(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   graphTest.addEdge("n1", "n2", "x");
	   graphTest.addEdge("n1", "n2", "x");
	   graphTest.addEdge("n1", "n2", "x");
	   assertEquals(1, graphTest.EdgeNumbers("n1", "n2"));

   }
	
	/*TestGetAllLabels=============================================================================*/	
   @Test(expected=IllegalArgumentException.class)
 	public void nodePassedIntoGetAllLabelIsNull(){
 	   graphTest.getAllLabels(null, null);
    }
  @Test(expected=IllegalArgumentException.class)
	public void nodePassedIntoGetAllLabelsDoesNotInGraph(){
	   graphTest.getAllLabels("n1", "n2");
   }
  @Test
  public void EmptyLabelBetween(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   assertEquals(0,graphTest.getAllLabels("n1", "n2").size());
  }
  @Test
  public void OnlyOneLabelBetween(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   graphTest.addEdge("n1", "n2", "x");
	   assertEquals(1,graphTest.getAllLabels("n1", "n2").size());
	   assertTrue(graphTest.checkEdge("n1", "n2"));
  }
	
  @Test
  public void MoreLabelBetween(){
	   graphTest.addNode("n1");
	   graphTest.addNode("n2");
	   graphTest.addEdge("n1", "n2", "x");
	   graphTest.addEdge("n1", "n2", "y");
	   graphTest.addEdge("n1", "n2", "z");
	   graphTest.addEdge("n1", "n2", "a");
	   graphTest.addEdge("n1", "n2", "b");
	   graphTest.addEdge("n1", "n2", "c");
	   assertEquals(6,graphTest.getAllLabels("n1", "n2").size());
	   assertTrue(graphTest.checkEdge("n1", "n2"));
  }
	/*TestContainsNode=============================================================================*/	
  @Test(expected=IllegalArgumentException.class)
	public void nodePassedIntoContainsNodeIsNull(){
	   graphTest.containsNode(null);
  }
  @Test
  public void testEmptyGraph(){
	  assertFalse(graphTest.containsNode("x"));
  }
  @Test
  public void tesOnlyOneNodeGraph(){
	  graphTest.addNode("n1");
	  assertFalse(graphTest.containsNode("n2"));
	  assertTrue(graphTest.containsNode("n1"));
  }
  @Test
  public void tesMoreNodesinGraph(){
	  graphTest.addNode("n1");
	  graphTest.addNode("n2");
	  graphTest.addNode("n3");
	  graphTest.addNode("n4");
	  graphTest.addNode("n5");
	  assertTrue(graphTest.containsNode("n2"));
	  assertTrue(graphTest.containsNode("n1"));
	  assertTrue(graphTest.containsNode("n3"));
	  assertTrue(graphTest.containsNode("n4"));
	  assertTrue(graphTest.containsNode("n5"));

  }
}
