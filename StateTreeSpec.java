

public interface StateTreeSpec {

	//take currentState as root node and generate new 4-level tree based on this root (root + 3 layers)
	public void generateTree(StateNode parent, int rootIndicator);
	
	//calculate the heuristic value for each leaf node and store in corresponding StateNode object
	public int calculateHeuristicForLeafNode(StateNode leafNode);
	
	//calculate chance for each individual node splitting children into 2 branches, sum of chances of all children of one parent needs to be 1
	public void calculateChances(StateNode[] children, int numChildren);
	
	//calculate total chance value for each branch and add value to each child's heuristic value
	public void calcChancesForBranches(StateNode[]children, StateNode parent, int numChildren, boolean isMax);
	
	//method that calls expectiminimax algorithm
	public void findBestMove(int[]coordinates);
	
	//expectiminimax algorithm to find optimal path for computer
	public double expectiminimax(StateNode parent, int depth, boolean isMaximizingPlayer);
	
	//compare parent state with chosen state to find coordinates of cell that computer needs to place its O in
	public void determineCoordinates(StateNode parent, StateNode child, int[]co);	
}
