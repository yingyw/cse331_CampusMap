//Yingying Wang
//CSE331 HW7
//SECTION AB
package hw7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import hw5.Graph;
import hw6.MarvelPaths;
/**
 * This is a class used to build graph by given file, find and 
 * print least cost path of two nodes in that graph by executing 
 * commands
 */
public class MarvelPaths2 {
	private static final int INITIAL_CAPACITY = 100;
	public static void main(String[] args) throws Exception {
		Graph<String, Double> graph = new Graph<String, Double>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of Marvel file");
		String fileName = sc.nextLine();
		graph = loadGraph(fileName);
		System.out.println("Starting character");
		String startChar = sc.nextLine();
		System.out.println("Ending character");
		String endChar = sc.nextLine();
		String start = startChar.replace('_', ' '); //replace all _ in commands with space
		String end = endChar.replace('_', ' ');
		if (!graph.containsNode(start) || !graph.containsNode(end)) {
			if (!graph.containsNode(start)) {
				System.out.println("unknown character " + startChar); // if given start node is node not inside graph
			}														  // print it out to claim that's unknown character
			if (!graph.containsNode(end)) {
				System.out.println("unknown character " + endChar);
			}
		} else {
			System.out.println("path from " + start + " to " + end + ":");
			List<String> paths = MarvelPaths2.findPath(graph, start, end);
		  	  if (paths == null) 
		  		  System.out.print("no path found");
		    	  else{
		  		  for(int i=2; i<paths.size();i++){
		  			System.out.print(paths.get(i-1));
		  			System.out.print(" to ");
		  			System.out.print(paths.get(i));
		  			  Set<Double> labels = graph.getAllLabels(paths.get(i-1), paths.get(i));
		  			  Double doubleEdge = labels.iterator().next();
		  			System.out.print(String.format(" with weight %.3f", doubleEdge));
		  			System.out.println();
		  		  }
		  		System.out.println(String.format("total cost: %.3f", MarvelPaths2.totalCost(paths, graph)));
		}
		sc.close();
		}
	}
	/**
	 * Use data in file with given filename to create a graph
	 * @param name of file that used to construct the graph
	 * @return a graph built by data in a file with given file name
	 */
	public static Graph<String,Double> loadGraph(String fileName){
		Graph<String, Double> loadedGraph = new Graph<String, Double>();
		Graph<String, String> oldGraph = MarvelPaths.loadGraph(fileName);
		for(String start :oldGraph.getAllNodes()){
			loadedGraph.addNode(start);
			for(String end: oldGraph.getAllEnds(start)){
				loadedGraph.addNode(end);
				int numLabel = oldGraph.EdgeNumbers(start, end);
				loadedGraph.addEdge(start, end, (double) 1/numLabel);
			}
		}
		return loadedGraph;
	}
	/**
	 * finds the least cost path from given start node to given end node
	 * using Dijkstra's algorithm
	 * @param <E>
	 * @param graph graph used to find path in
	 * @param start node used to find path
	 * @param end node used to find path
	 * @throws IllegalArgumentException if given nodes are not inside graph
	 * @return List contains least cost of the path and passed node of this least cost path
	 *  from given start node to end node, returns null if there is no path between given nodes.
	 */
	public static <E> List<E> findPath(final Graph<E, Double> graph, E start, E end){
		if (!graph.containsNode(start) || !graph.containsNode(end)) {
			throw new IllegalArgumentException();
		}
		Queue<List<E>> active = new PriorityQueue<List<E>>(
				INITIAL_CAPACITY, new Comparator<List<E>>() {
					@Override
					public int compare(List<E> first, List<E> second) {
						return Double.compare(totalCost(first, graph),
								totalCost(second, graph));
					}
				});
		Set<E> finished = new HashSet<E>();
		List<E> initial = new ArrayList<E>();
		initial.add(start);
		active.add(initial);
		while(!active.isEmpty()){
			List<E> minPath = active.remove();
			E minDest = minPath.get(minPath.size()-1);
			if(minDest.equals(end)){
				return minPath;
			}
			if(finished.contains(minDest)){
				continue;
			}
			for(E children: graph.getAllEnds(minDest)){
				if(!finished.contains(children)){
					List<E> list = new ArrayList<E>();
					for(E path: minPath){
						list.add(path);
					}
					list.add(children);
					active.add(list);
				}
			}
			finished.add(minDest);
		}
		return null;
		
	}
	/**
	 * Computes total cost of the given path
	 * @param paths a list contains a path with passed node
	 * @param graph where the path inside
	 * @return total cost of given path
	 */
	public static<E> double totalCost(List<E> paths, Graph<E,Double> graph){
		double result = 0.0;
		for(int i=0;i<paths.size()-1;i++){
			TreeSet<Double> set = new TreeSet<Double>();
			set.addAll(graph.getAllLabels(paths.get(i), paths.get(i+1)));
			double newAdd = set.first();
			result+=newAdd;
		}
		return result;
	}
}
