package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Parser utility to load the map path dataset
 * This class doesn't represent ADT so we don't need to comment on AF and RI
 */
public class PathsPaser {
	/**
	 * Reads the map path dataset.
	 * Each line of the input line file contains a coordinate of start point,
	 * or a coordinate of a end point plus a the path cost from start point to
	 * end point.
	 * @requires file is well-formed, with each line containing exactly 2 tokens
	 * 			 as start point separated by, or two tokens including end point and 
	 * 			 path cost separated by ":". 
	 * @param filename  filename the file that will be read
	 * @param paths paths map from a start point to end point, and internal map
	 * 		  from a end point to path cost
	 */
	public static void parseData(String filename, Map<Node,Map<Node,Double>> paths)
			throws Exception {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String inputLine = reader.readLine();
			String start = inputLine;
			String[] tokens = start.split(",");
			if(tokens.length!=2){
				throw new Exception();
			}
			// tokens length should be 2 including x coordinate and y coordinate of a point
			Node startNode = new Node(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
			paths.put(startNode, new HashMap<Node,Double>());
			while ((inputLine = reader.readLine()) != null) {
				if(!inputLine.startsWith("\t")){
					start = inputLine;
					tokens = start.split(",");
					if(tokens.length!=2){
						throw new Exception();
					}
					startNode = new Node(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
					paths.put(startNode, new HashMap<Node,Double>());
				}else{
					//first one in tokens is end point, second one in tokens is distance from start to end
					tokens = inputLine.split(":");
					if(tokens.length!=2){
						throw new Exception();
					}
					String end = tokens[0].trim();
					String[] split = end.split(",");
					//get end point coordinate and split it
					Node endNode = new Node(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
					paths.get(startNode).put(endNode, Double.parseDouble(tokens[1]));
					
				}				
			}
		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
