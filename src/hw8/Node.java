package hw8;
/**
 * This class represents a node includes a point's x-coordinate
 * and point's y-coordinate
 */
//  Representation invariant: x!=null && y!=null
//  Abstract Function: This class represents a location of a point,
// 					  where x represents x coordinate and y represents
//					  y coordinate.
public class Node implements Comparable<Node> {
	public double x;
	public double y;
	public Node(double x, double y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Override equals to make two nodes are equal when both of their
	 * x coordinate and y coordinate are equal
	 */
	@Override
	public boolean equals(Object o){
		if(o==null||!(o instanceof Node)){
			return false;
		}
		Node other = (Node) o;
		return this.x-other.x== 0 && this.y-other.y==0;
	}
	/**
	 * Override hasCode to make two nodes which are equal has same 
	 * hashcode
	 */
	@Override
	public int hashCode(){
		int hashX = (int)Math.round(this.x);
		int hashY = (int)Math.round(this.y);
		return (hashX+hashY)*17;
	}
	/**
	 * override compareTo to make node to be comparable
	 */
	@Override
	public int compareTo(Node other) {
		if(this.x>other.x){
			return 1;
		}
		if(this.y>other.y){
			return 1;
		}
		if(this.x<other.x){
			return -1;
		}
		if(this.y<other.y){
			return -1;
		}
		return 0;
	}
}
