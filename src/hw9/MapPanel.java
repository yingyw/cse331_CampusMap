package hw9;

import hw8.Node;
import hw8.UWMap;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is called MapPanel showing the map, tool bar and text on the
 * panel. It also shows the the selected start building and selected end
 * building and the path between these two buildings.
 * 
 * @author yingyw
 * 
 */
public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String imageMap = "src/hw8/data/campus_map.jpg";
	private BufferedImage campusMapImage;
	private Node startNode;
	private Node endNode;
	private JButton direction;
	private JButton reset;
	private boolean draw;
	private String startBuild;
	private String endBuild;

	public MapPanel() {
		try {
			campusMapImage = ImageIO.read(new File(imageMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel from = new JLabel("From");
		add(from);
		final JComboBox<String> start = new JComboBox<String>();
		for (String shortName : CampusPathsMain.shortToFull.keySet()) {
			start.addItem(shortName);
		}
		start.addActionListener(new ActionListener() {
			/**
			 * perform action to mark the start building.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startBuild = start.getSelectedItem().toString();
				startNode = CampusPathsMain.coordinate.get(startBuild);
				draw = false;
				repaint();
			}
		});
		add(start);
		JLabel to = new JLabel("To");
		add(to);
		final JComboBox<String> end = new JComboBox<String>();
		for (String shortName : CampusPathsMain.shortToFull.keySet()) {
			end.addItem(shortName);
		}
		end.addActionListener(new ActionListener() {
			/**
			 * perform action to mark the end building.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				endBuild = end.getSelectedItem().toString();
				endNode = CampusPathsMain.coordinate.get(endBuild);
				draw = false;
				repaint();
			}
		});
		add(end);

		direction = new JButton("Get Direction");
		add(direction);
		direction.addActionListener(new ButtonListener());
		reset = new JButton("Reset");
		add(reset);
		reset.addActionListener(new ButtonListener());
		draw = false;

		// make cursor to be a hand shape
		Cursor hand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		setCursor(hand);
	}

	/**
	 * Override method to mark out the start building, end building when it has
	 * been selected, and then print out the shortest path between the selected
	 * buildings if getDirection button is clicked.
	 */
	@Override
	public void paintComponent(Graphics graph) {
		graph.drawImage(campusMapImage, 0, 0, getWidth(), getHeight(), null);
		// set making building color to be yellow
		graph.setColor(Color.GREEN);
		// mark strat and end building out
		if (startNode != null) {
			graph.fillOval(getX(startNode) - 10, getY(startNode) - 10, 20, 20);
		}
		if (endNode != null)
			graph.fillOval(getX(endNode) - 10, getY(endNode) - 10, 20, 20);
		// draw path from start building to end building.
		if (draw) {
			if (startNode != null && endNode != null) {
				try {
					List<Node> path = shortestPath();
					for (int i = 1; i < path.size(); i++) {
						graph.setColor(Color.BLUE);
						graph.drawLine((int) getX(path.get(i - 1)),
								(int) getY(path.get(i - 1)),
								(int) getX(path.get(i)),
								(int) getY(path.get(i)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Get the shortest path from selected starting node to selected ending node
	 * 
	 * @return a list of passed node of path
	 */
	private List<Node> shortestPath() throws Exception {
		UWMap uwMap = new UWMap();
		return uwMap.findPath(startBuild, endBuild);
	}

	/**
	 * Get real x coordinate according to the window size
	 * 
	 * @param node
	 *            x position need to be transferred
	 * @return the real x coordinate of given node
	 */
	private int getX(Node node) {
		return (int) (getWidth() * node.x / campusMapImage.getWidth());
	}

	/**
	 * Get real u coordinate according to the window size
	 * 
	 * @param node
	 *            y position need to be transferred
	 * @return the real y coordinate of given node
	 */
	private int getY(Node node) {
		return (int) (getHeight() * node.y / campusMapImage.getHeight());
	}

	/**
	 * inner class to perform different action events
	 * 
	 */
	class ButtonListener implements ActionListener {
		/**
		 * perform certain actions according to different action events
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == direction) {
				if (startNode != null && endNode != null) {
					draw = true;
					repaint();
				}
			} else if (e.getSource() == reset) {
				draw = false;
				startNode = null;
				endNode = null;
				repaint();
			}

		}

	}

}
