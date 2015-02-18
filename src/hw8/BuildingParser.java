package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
/**
 * This class doesn't represent ADT so we don't need to comment on AF and RI
 * Parser utility to load the map buildings dataset
 *
 */
public class BuildingParser {
	/**
	 * Reads map buildings dataset
	 * Each line of the input file contains building's short name, full name and it's x and y coordinate,
	 * separated by a space
	 * @requires file is well-formed, with each line containing exactly four tokens separated by
	 * 			 a space
	 * @param filename the file that will be read
	 * @param shortToFull sorted buildings names map 
	 * @param coordinate coordinate map from short name of a building to coordinate of a building
	 * @modifies shortToFull, coordinate
	 * @effects fills shortToFull with all buildings short name to buildings full name
	 * @effects fills coordinate with a map from each buildings short name to each buildings coordinate.
	 */
	public static void parseData(String filename,
			Map<String, String> shortToFull, Map<String, Node> coordinate)
			throws Exception {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			
			//Construct the map form short name to full name, and construct
			// the map from short name to coordinate, one pair of two maps at once
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				String[] tokens = inputLine.split("\t");
				// throws exception if tokens length is not 4
				// tokens should be short name, full name, x_coordinate and y_coordinate.
				if (tokens.length != 4) {
					throw new Exception("Line should contain exactly three tabs: "
							+ inputLine);
				}
				String shortName = tokens[0];
				String fullName = tokens[1];
				shortToFull.put(shortName, fullName);
				Node position = new Node(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
				//map short name to its responding coordinate 
				coordinate.put(shortName, position);
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
