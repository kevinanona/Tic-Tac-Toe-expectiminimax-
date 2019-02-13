

public class StateNode {

	private StateNode [] children = new StateNode[9];
	private int numChildren = 0;	
	private int [][] stateOfNode = new int[3][3];	
	private double heuristicValue = 0;
	private double chance = -50.0;
	private int baseChildIndex;
	
	public StateNode(int[][] sArray){
		setStateArray(sArray);
	}
	
	//set a particular square in 3x3 grid
	public void setValueOfSquare(int row, int col, int value){
		stateOfNode[row][col] = value;
	}
	
	public int getValueOfSquare(int row, int col){
		return stateOfNode[row][col];
	}
	
	//set the state of this StateNode
	public void setStateArray(int [][] array){
		for(int x = 0; x < array.length; x++){
			for(int y = 0; y < array.length; y++){
				stateOfNode[x][y] = array[x][y];
			}
		}
	}
	
	//return state of this node
	public int[][] getStateArray(){
		return stateOfNode;
	}
	
	//set heuristic value of this StateNode
	public void setHeuristicValue(double hValue) {	
		heuristicValue = hValue;		
	}
	
	public double getHeuristicValue() {
		return heuristicValue;
	}
	
	//each StateNode has a chance associated with it
	public void setChance(double chanceValue) {
		chance = chanceValue;
	}
	
	public double getChance() {
		return chance;
	}
	
	//store all children of this node into children array
	public void setChildren(StateNode[] array, int numChildren){
		
		for(int i = 0; i < numChildren; i++){
			if(array[i] != null){
				children[i] = array[i];
			}else{
				children[i] = null;
			}		
		}
	}
	
	//return children array
	public StateNode[] getChildren(){
		return children;
	}
	
	//number of children of this node
	public void setNumChildren(int num){
		numChildren = num;
	}
	
	public int getNumChildren(){
		return numChildren;
	}
	
	//save the index of the first child of this node
	public void setBaseChildIndex(int ind){
		baseChildIndex = ind;
	}
	
	public int getBaseChildIndex(){
		return baseChildIndex;
	}
	
	//print state of this node
	public void printState(){
		
		for(int x = 0; x < stateOfNode.length; x++){
			for(int y = 0; y < stateOfNode.length; y++){
				System.out.print(stateOfNode[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}	
}

