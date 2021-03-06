//Yingying Wang
//CSE331 HW7
//SECTION AB
package hw7.test;

import hw5.Graph;
import hw6.test.HW6TestDriver;
import hw7.MarvelPaths2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * This class implements a testing driver which reads test scripts
 * from files for your graph ADT and improved MarvelPaths application
 * using Dijkstra's algorithm.
 **/
public class HW7TestDriver {


    public static void main(String args[]) {
    	 try {
             if (args.length > 1) {
                 printUsage();
                 return;
             }

             HW6TestDriver td;

             if (args.length == 0) {
                 td = new HW6TestDriver(new InputStreamReader(System.in),
                                        new OutputStreamWriter(System.out));
             } else {

                 String fileName = args[0];
                 File tests = new File (fileName);

                 if (tests.exists() || tests.canRead()) {
                     td = new HW6TestDriver(new FileReader(tests),
                                            new OutputStreamWriter(System.out));
                 } else {
                     System.err.println("Cannot read from " + tests.toString());
                     printUsage();
                     return;
                 }
             }

             td.runTests();

         } catch (IOException e) {
             System.err.println(e.toString());
             e.printStackTrace(System.err);
         }
    }
    
    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("to read from a file: java hw7.test.HW7TestDriver <name of input script>");
        System.err.println("to read from standard in: java hw7.test.HW7TestDriver");
    }
    
    /** String -> Graph: maps the names of graphs to the actual graph **/
    private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String,Double>>();
    private final PrintWriter output;
    private final BufferedReader input;

    
    public HW7TestDriver(Reader r, Writer w) {
    	input = new BufferedReader(r);
        output = new PrintWriter(w);
    }
    /**
     * @effects Executes the commands read from the input and writes results to the output
     * @throws IOException if the input or output sources encounter an IOException
     **/
    public void runTests()
    	      throws IOException
    	  {
    	      String inputLine;
    	      while ((inputLine = input.readLine()) != null) {
    	          if ((inputLine.trim().length() == 0) ||
    	              (inputLine.charAt(0) == '#')) {
    	              // echo blank and comment lines
    	              output.println(inputLine);
    	          }
    	          else
    	          {
    	              // separate the input line on white space
    	              StringTokenizer st = new StringTokenizer(inputLine);
    	              if (st.hasMoreTokens()) {
    	                  String command = st.nextToken();

    	                  List<String> arguments = new ArrayList<String>();
    	                  while (st.hasMoreTokens()) {
    	                      arguments.add(st.nextToken());
    	                  }

    	                  executeCommand(command, arguments);
    	              }
    	          }
    	          output.flush();
    	      }
    	  }
    private void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if(command.equals("LoadGraph")){
          	  buildGraph(arguments);
            } else if(command.equals("FindPath")){
          	  findPath(arguments);
            }else {
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }
    /**
     * build graph using given file
     * @param arguments includes graph name and file name
     * @throw exception if given argument size is not 2
     */
    private void buildGraph(List<String> arguments){
  	  if(arguments.size()!=2){
  		  throw new CommandException("Bad argumetns to loadGraph: " + arguments);
  	  }
  	  String graphName = arguments.get(0);
  	  String fileName = arguments.get(1);
  	  buildGraph(graphName,fileName);
    }
    /**
     * helper method to build graph in given name using given file
     * @param graphName name of graph to be built
     * @param fileName that used to build graph
     */
    private void buildGraph(String graphName, String fileName){
  	  Graph<String, Double> graph=MarvelPaths2.loadGraph("src/hw7/data/" + fileName);
  	  graphs.put(graphName, graph);
  	  output.println("loaded graph " + graphName);
  	  
    }
    /**
     * find closest path of given two nodes in given graph
     * @param arguments includes start node, end node and graph name
     * @throws CommandException is given arguments size is not 3
     */
    private void findPath(List<String> arguments){
  	  if(arguments.size()!=3){
  		  throw new CommandException("Bad argumetns to loadGraph: " + arguments);
  	  }
  	  String graph = arguments.get(0);
  	  String start = arguments.get(1);
  	  String end = arguments.get(2);
  	  findPath(graph, start,end);
    }
    /**
     * Helper method used to find shortest path of given start and end nodes
     * in given graph
     * @param graphName
     * @param start as start node of path
     * @param end as end node of path
     */
    private void findPath(String graphName, String startChar, String endChar){

    	Graph<String,Double> graph = graphs.get(graphName);;
		String start = startChar.replace('_', ' '); //replace all _ in commands with space
		String end = endChar.replace('_', ' ');
		if (!graph.containsNode(start) || !graph.containsNode(end)) {
			if (!graph.containsNode(start)) {
				output.println("unknown character " + startChar); // if given start node is node not inside graph
			}														  // print it out to claim that's unknown character
			if (!graph.containsNode(end)) {
				output.println("unknown character " + endChar);
			}
		} else {
			output.println("path from " + start + " to " + end + ":");
			List<String> paths = MarvelPaths2.findPath(graph, start, end);
			//output.print(paths);
			double totalCost = 0.0;
		  	  if (paths == null) 
		  		  output.print(String.format("total cost: %.3f", totalCost));
		    	  else{
		  		  for(int i=1; i<paths.size();i++){
		  			output.print(paths.get(i-1));
		  			output.print(" to ");
		  			output.print(paths.get(i));
		  			  //output.print(" via ");
		  			  Set<Double> labels = graph.getAllLabels(paths.get(i-1), paths.get(i));
		  			  Double doubleEdge = labels.iterator().next();
		  			  //double doubleEdge = Double.parseDouble(label);
		  			  totalCost += doubleEdge;
		  			output.print(String.format(" with weight %.3f", doubleEdge));
		  			output.println();
		  		  }
		  		output.println(String.format("total cost: %.3f", totalCost));
		}

		}
  	  
    }
  /**
   * Create graph in given graph name
   * @param arguments includes graph name
   * @throw CommandException if given arguments size is not 1
   */
    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }
  /**
   * helper method used to construct graph in given name
   * @param graphName name of graph constructed
   */
    private void createGraph(String graphName) {
        graphs.put(graphName, new Graph<String, Double>());
        output.println("created graph " + graphName);
    }
  /**
   * Adds given node to graph, if given arguments size is not 2 throw exception
   * @param arguments includes node to be added and graph name to be added node
   * @throws CommandException if given argument size is not 2
   */
    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    /**
     * helper method used to add node to graph
     * @param graphName name of graph to add node on
     * @param nodeName name of node to be added
     * @effect add node in graph
     * @modifies graph
     */
    private void addNode(String graphName, String nodeName) {
        // Insert your code here.
         Graph<String, Double> g = graphs.get(graphName);
         g.addNode(nodeName);
         output.println("added node "+nodeName+" to "+graphName);
    }
    

  /**
   * add edge in given graph between given nodes with given label
   * @param arguments includes graph name, parent name, child name and label
   * @throws CommandException if given arguments size is not 4
   */
    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }
  /**
   * Helper method to add edge between nodees
   * @param graphName name of graph to add edge
   * @param parentName name of start node
   * @param childName name of end node
   * @param edgeLabel name of label on edge
   * @modifies graph
   * @effects add edge between two nodes
   */
    private void addEdge(String graphName, String parentName, String childName,
            double edgeLabel) {
        // Insert your code here.
    
         Graph<String, Double> g = graphs.get(graphName);
         g.addEdge(parentName,childName, edgeLabel);
        // double doubleEdge = Double.parseDouble(edgeLabel);
         output.println(String.format("added edge %.3f", edgeLabel) +" from "+parentName+" to "+childName+" in "+graphName);
    }
  /**
   * List all nodes in given graph name
   * @param arguments include graph name
   * @throws CommandException if given arguments size is not 1
   */
    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }
  /**
   * Helper method used to list all node in given graph name
   * @param graphName name of graph to list node
   */
    private void listNodes(String graphName) {
        // Insert your code here.
        Graph<String, Double> g = graphs.get(graphName);
        Set<String> sort = new TreeSet<String>();
        for(String s:g.getAllNodes()){
        	sort.add(s);
        }
        output.print(graphName+" contains:");
        for(String node:sort){
        	output.print(" "+node);
        }
        output.println();
    }
    /**
     * List children of given parent in given graph
     * @param arguments includes name of graph and name of parent
     * @throws CommandException if given arguments size is not 2
     */
    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }
    /**
     * Helper method to List children of given parent in given graph
     * @param graphName the name of graph needed to get children in
     * @param parentName the name of parent need to get children of
     */
    private void listChildren(String graphName, String parentName) {
    	Graph<String, Double> g = graphs.get(graphName);
        output.print("the children of "+ parentName+" in "+graphName+" are: "); 
         for(String s : g.getAllEnds(parentName)){  
        	 Set<Double> set = new TreeSet<Double>();
        	 for(Double label:g.getAllLabels(parentName, s)){
        		set.add(label);
        	 }
        	 for(Double sortLabel:set){
            	 output.print(s+String.format("(%.3f",sortLabel)+") ");
        	 }
         }
         output.println();
    }
    

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }
        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
