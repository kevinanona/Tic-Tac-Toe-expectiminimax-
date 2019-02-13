

import java.util.Random;
import java.lang.Math;


public class StateTree implements StateTreeSpec {
	
	private int counterX, counterO;
	private int PLAYER_X = 1;
	private int PLAYER_O = 2;
	public StateNode[] stateTree = new StateNode[300]; //4 layers -> root (passed in) + three layers
	int xTotalCount, oTotalCount;
	Random random;
	ArrayState elimination = new ArrayState();
	int baseIndex = 1;
	int indexFirstLayerChild = 1;//store first layer children in beginning of stateTree array
	int indexSecondLayerChild = 10;//store second layer children in stateTree starting at position 10
	int indexThirdLayerChild = 60;
	int maxDepth = 2;
	
	//generate three layers depending on parent StateNode which becomes root of tree
	//rootIndicator indicates depth 
	public void generateTree(StateNode parent, int rootIndicator) {
		
		int childCounter = 0;//count how many children one node has
		StateNode [] layerChildren = new StateNode[9]; //store children of this parent
		StateNode [] temp;
		int heuristic;
		int player = 0;
		boolean exists;			
		int [][] currentState = parent.getStateArray(); //get game state of this parent
		
		//switch between X and O
		if(rootIndicator == 0 || rootIndicator == 2){
			player = PLAYER_O;
		}else if(rootIndicator == 1){
			player = PLAYER_X;
		}
			
		//generate children for current parent node and store them in temp array
		for(int i = 0; i < currentState.length; i++){
			for(int j = 0; j < currentState.length; j++){
				
				if(currentState[i][j] == 0){
					
					//create new StateNode object and initialize with parent game state
					StateNode node = new StateNode(currentState);
					//add symbol of player to generate possible move
					node.setValueOfSquare(i, j, player);
										
					//if leaf node calculate heuristic using heuristic function
					if(rootIndicator == maxDepth){
						heuristic = calculateHeuristicForLeafNode(node);
						node.setHeuristicValue(heuristic);
					}
					
					//pass state of node to ArrayState class to see if node is a duplicate of another node
					exists = elimination.stateExists(node.getStateArray());
					
					//if equivalent state doesn't already exist, store node in children array
					if(!exists){
						layerChildren[childCounter] = node;
						childCounter++;
					}				
				}	
			}
		}
			
		//shrink children array if there are less children than size of array
		if(childCounter < 9){
			temp = new StateNode[childCounter];
			for(int w = 0; w < childCounter; w++){
				temp[w] = layerChildren[w];
			}
			layerChildren = new StateNode[childCounter];
			for(int w = 0; w < childCounter; w++){
				layerChildren[w] = temp[w];
			}
		}		
		
		//calculate chances for children of this parent node (individual chances for two branches that sum up to 1)
		calculateChances(layerChildren, childCounter);
		
		parent.setChildren(layerChildren, childCounter);
		parent.setNumChildren(childCounter);
			
		//store root of the tree in first cell in stateTree array, its children are stored right after it
		if(rootIndicator == 0){
			stateTree[0] = parent;
			stateTree[0].setBaseChildIndex(1);
		}
			
		//store children in array stateTree, first layer children are stored in the beginning of array (root's children)
		//second layer children are stored starting at index 10 & third layer children are stored starting at index 60
		for(int x = 0; x < layerChildren.length; x++){
			
			if(rootIndicator == 0){
				stateTree[indexFirstLayerChild] = layerChildren[x];
				stateTree[indexFirstLayerChild].setBaseChildIndex(indexSecondLayerChild); //keep track of base index where children are stored
				indexFirstLayerChild++;
			}else if(rootIndicator == 1){
				stateTree[indexSecondLayerChild] = layerChildren[x];
				stateTree[indexSecondLayerChild].setBaseChildIndex(indexThirdLayerChild);
				indexSecondLayerChild++;
			}else if(rootIndicator == 2) {
				stateTree[indexThirdLayerChild] = layerChildren[x];
				indexThirdLayerChild++;
			}
		
			//if rootIndicator == 0, then only first layer children have been computed, so go on and compute children for 2nd layer
			//if rootIndicator == 1, go ahead and compute 3rd layer children (3 layers are generated recursively)
			if(rootIndicator == 0){
				generateTree(layerChildren[x], 1);
			}else if(rootIndicator == 1) {
				generateTree(layerChildren[x], 2);
			}
		}	
	}	
		
	//initialize node, set each cell to 0
	public void initializeNode(StateNode n){		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				n.setValueOfSquare(x, y, 0);
			}
		}
	}
	
	// split children into two branches and assign chance to each branch, chance values of one branch add up to 1
	public void calculateChances(StateNode[]children, int numChildren) {
			
		random = new Random();
		int randValue = random.nextInt(39) + 10;
		int firstHalf, secondHalf;
		double[]chanceValues = new double[numChildren];
		double tempValue;
		
		if(numChildren != 0){
		
			//split children into 2 branches
			if(numChildren % 2 == 0){
				firstHalf = numChildren/2;
				secondHalf = firstHalf;
			}else{
				firstHalf = (numChildren - 1)/2;
				secondHalf = firstHalf + 1;
			}
			
			//assign chance values to each child of left branch
			if(firstHalf == 0){
				chanceValues[0] = 0.0;
			}else if(firstHalf == 1){
				chanceValues[0] = 1.0;
			}else if(firstHalf == 2){
				chanceValues[0] = (double) randValue/100.0;
				chanceValues[1] = (1.0 - chanceValues[0]);
				
			}else if(firstHalf == 3){
				chanceValues[0] = (double) randValue/100.0;
				tempValue = 1.0 - chanceValues[0];
				
				tempValue = tempValue/2.0;
					
				chanceValues[1] = (tempValue - 0.05);
				chanceValues[2] = (tempValue + 0.05);
				
			}else if(firstHalf == 4){
				chanceValues[0] = (double) randValue/100.0;
				tempValue = 1.0 - chanceValues[0];
				
				tempValue = tempValue/3.0;
				
				chanceValues[1] = tempValue;
				chanceValues[2] = (tempValue - 0.05);
				chanceValues[3] = (tempValue + 0.05);				
			}
			
			//store chance values for left branch in first half of children
			for(int x = 0; x < firstHalf; x++){
				children[x].setChance(chanceValues[x]);	
			}
				
			randValue = random.nextInt(39) + 10;
			
			//assign chance values to each child of right branch
			if(secondHalf == 1){
				chanceValues[firstHalf] = 1.0;				
			}else if(secondHalf == 2){
				chanceValues[firstHalf] = (double) randValue/100.0;
				chanceValues[firstHalf+1] = (1.0 - chanceValues[firstHalf]);
						
			}else if(secondHalf == 3){
				chanceValues[firstHalf] = (double) randValue/100.0;
				tempValue = 1.0 - chanceValues[firstHalf];
						
				tempValue = tempValue/2.0;
							
				chanceValues[firstHalf+1] = (tempValue - 0.05);
				chanceValues[firstHalf+2] = (tempValue + 0.05);			
									
			}else if(secondHalf == 4){
				chanceValues[firstHalf] = (double) randValue/100.0;
				tempValue = 1.0 - chanceValues[firstHalf];
						
				tempValue = tempValue/3.0;
						
				chanceValues[firstHalf+1] = tempValue;
				chanceValues[firstHalf+2] = (tempValue - 0.05);
				chanceValues[firstHalf+3] = (tempValue + 0.05);	
						
			}else if(secondHalf == 5){	
				chanceValues[firstHalf] = (double) randValue/100.0;
				tempValue = 1.0 - chanceValues[firstHalf];		
						
				tempValue = tempValue/4.0;	
						
				chanceValues[firstHalf+1] = (tempValue - 0.05);
				chanceValues[firstHalf+2] = (tempValue + 0.05);
				chanceValues[firstHalf+3] = (tempValue - 0.04);
				chanceValues[firstHalf+4] = (tempValue + 0.04);	
			}
					
			//store chance value for right branch in second half of children
			for(int x = firstHalf; x < numChildren; x++){
				children[x].setChance(chanceValues[x]);
			}	
		}
	}
	
	//calculate actual/overall heuristic value for each branch (multiply chance value with each 
	//heuristic value of the same branch, add them up and add to heuristic value of children)
	public void calcChancesForBranches(StateNode[] children, StateNode parent, int numChildren, boolean isMax) {
		
		double chanceLeft = 0.0;
		double chanceRight = 0.0;
		int firstHalf;
		double newHeuristic;
		
		//split children into two branches
		if(numChildren % 2 == 0){
			firstHalf = numChildren/2;
		}else{
			firstHalf = (numChildren - 1)/2;
		}
		
		//calculate chance for left branch
		for(int x = 0; x < firstHalf; x++){	
			chanceLeft = chanceLeft + children[x].getChance() * children[x].getHeuristicValue();
		}	
		
		//calculate chance for right branch
		for(int x = firstHalf; x < numChildren; x++){		
			chanceRight = chanceRight + children[x].getChance() * children[x].getHeuristicValue();
		}
		
		//add a bias to the branch with the highest value to ensure child is picked from that branch
		//if max layer, add 100 to become maximum; if min layer, subtract 100 to become minimum
		if(isMax) {
			if(chanceLeft > chanceRight) {
				chanceLeft = chanceLeft + 100.0;
			}else {
				chanceRight = chanceRight + 100.0;
			}
		}else {
			if(chanceLeft > chanceRight) {
				chanceLeft = chanceLeft - 100.0;
			}else {
				chanceRight = chanceRight - 100.0;
			}
		}
				
		//add chance for left branch to each left child's heuristic value
		for(int x = 0; x < firstHalf; x++){
			newHeuristic = children[x].getHeuristicValue() + chanceLeft;
			children[x].setHeuristicValue(newHeuristic);		
		}
		
		//add chance for right branch to each right child's heuristic value
		for(int x = firstHalf; x < numChildren; x++){
			newHeuristic = children[x].getHeuristicValue() + chanceRight;
			children[x].setHeuristicValue(newHeuristic);			
		}	
	}
	
	// finds the best move to take for Player_O (computer) by calling expectiminimax method
	public void findBestMove(int [] coordinates){
		
		//call expectiminimax to find best move for playerO (computer)
		double maxValue = expectiminimax(stateTree[0], 0, true);
		
		//get index of root node's first child
		int childIndex = stateTree[0].getBaseChildIndex();
		
		for(int x = 0; x < stateTree[0].getNumChildren(); x++){
			
			//determine coordinates of the cell where the computer should place its symbol
			if(stateTree[childIndex].getHeuristicValue() == maxValue){
				determineCoordinates(stateTree[0], stateTree[childIndex], coordinates);
				break;
			}
			childIndex++;
		}
	}
	
	//find the best move for computer using expectiminimax and considering random chances for each layer
	public double expectiminimax(StateNode parent, int depth, boolean isMaximizingPlayer){
		
		double bestValue;
		double value;
		int numChildren = parent.getNumChildren();
		int baseIndex = parent.getBaseChildIndex();
		int childIndex;
		
		//base case
		if(depth == 3){			
			return parent.getHeuristicValue();
		}
		
		//player X, Max layer
		if(isMaximizingPlayer){
			
			bestValue = Double.NEGATIVE_INFINITY;
			StateNode[] children = new StateNode[numChildren];
			
			childIndex = baseIndex;
			
			for(int x = 0; x < numChildren; x++){		
				value = expectiminimax(stateTree[childIndex], depth + 1, false);//recursive call to expectiminimax function			
				stateTree[childIndex].setHeuristicValue(value);
				children[x] = stateTree[childIndex];//save each child in children array to compute heuristic values that include chance values
				childIndex++;
			}
			
			//calculate new heuristic values that include branch chance values & bias
			calcChancesForBranches(children, parent, numChildren, true);
			
			childIndex = baseIndex;
			
			//update stateTree
			for(int i = 0; i < numChildren; i++){				
				stateTree[childIndex] = children[i];
				childIndex++;
			}
			
			childIndex = baseIndex;
			
			//find max value of all children of this parent
			for(int y = 0; y < children.length; y++){			
				bestValue = Math.max(bestValue, stateTree[childIndex].getHeuristicValue());
				childIndex++;
			}
			
			//return the maximum value of all children of this parent
			return bestValue;
			
		//player O, Min layer
		}else{
			
			bestValue = Double.POSITIVE_INFINITY;
			StateNode[] children = new StateNode[numChildren];
			childIndex = baseIndex;
			
			for(int x = 0; x < numChildren; x++){				
				value = expectiminimax(stateTree[childIndex], depth + 1, true);//recursive call			
				stateTree[childIndex].setHeuristicValue(value);
				children[x] = stateTree[childIndex];//save each child in children array to compute heuristic values that include chance values
				childIndex++;
			}
			
			//calculate new heuristic values that include branch chance values & bias
			calcChancesForBranches(children, parent, numChildren, false);
			
			childIndex = baseIndex;
			
			//update stateTree
			for(int i = 0; i < numChildren; i++){		
				stateTree[childIndex] = children[i];
				childIndex++;
			}
			
			childIndex = baseIndex;
			
			//find min value of all children of this parent
			for(int y = 0; y < children.length; y++){
				bestValue = Math.min(bestValue, stateTree[childIndex].getHeuristicValue());
				childIndex++;
			}
				
			//return minimum value of all children of this parent
			return bestValue;
		}
	}
	
	// compare parent state with child state to determine coordinates of cell that computer should place its symbol in
	public void determineCoordinates(StateNode parent, StateNode child, int[]co){
		
		int[][]parentState = parent.getStateArray();
		int[][]childState = child.getStateArray();
		
		for(int x = 0; x < parentState.length; x++){
			for(int y = 0; y < parentState.length; y++){
				
				if(childState[x][y] == PLAYER_O && parentState[x][y] != PLAYER_O){
					co[0] = x;
					co[1] = y;
				}			
			}
		}	
	}
	
	//calculate heuristic values for leaf nodes using heuristic function
	//heuristic function: add rows, columns and diagonals that O can complete and subtract sum of rows, columns, diagonals X can complete
	public int calculateHeuristicForLeafNode(StateNode leafNode) {
				
		int hValue;
		
		xTotalCount = 0;
		oTotalCount = 0;
		
		countRows(leafNode.getStateArray());
		countColumns(leafNode.getStateArray());
		countDiagonals(leafNode.getStateArray());
		
		hValue = oTotalCount - xTotalCount;
		
		return hValue;		
	}
	
	//count rows x and o can complete based on passed state
	public void countRows(int [][] stateArray){
		
        for(int row = 0; row < stateArray.length; row++){

            counterX = 0;
            counterO = 0;

            for(int column = 0; column < stateArray.length; column++){

                if(stateArray[row][column] == PLAYER_X){
                    counterX++;
                }else if(stateArray[row][column] == PLAYER_O){
                    counterO++;
                }
            }
            
            //if there is at least one X and no O's in this row, then X can complete this row
            if(counterX > 0 && counterO == 0){
            	xTotalCount++;
            //if there is at least one O and no X in this row, then O can complete this row
            }else if(counterO > 0 && counterX == 0){
            	oTotalCount++;
            }
            
        }
    }
	
	//count columns that x and o can complete based passed state
	public void countColumns(int [][] stateArray){
		
        for(int column = 0; column < stateArray.length; column++){

            counterX = 0;
            counterO = 0;

            for(int row = 0; row < stateArray.length; row++){

                if(stateArray[row][column] == PLAYER_X){
                    counterX++;
                }else if(stateArray[row][column] == PLAYER_O){
                    counterO++;
                }
            }
            
            if(counterX > 0 && counterO == 0){
            	xTotalCount++;
            }else if(counterO > 0 && counterX == 0){
            	oTotalCount++;
            }
        }
    }
	
	//count diagonals that x and o can complete based on passed state
	public void countDiagonals(int [][] stateArray){

        counterX = 0;
        counterO = 0;

        //diagonal left to right
        for(int row = 0; row < stateArray.length; row++){
            for(int column = 0; column < stateArray.length; column++){

                if(row == column){

                    if(stateArray[row][column] == PLAYER_X){
                        counterX++;
                    }else if(stateArray[row][column] == PLAYER_O){
                        counterO++;
                    }
                }
            }
        }
        
        if(counterX > 0 && counterO == 0){
        	xTotalCount++;
        }else if(counterO > 0 && counterX == 0){
        	oTotalCount++;
        }
        
        //diagonal right to left
        int y = 0;
        int x = -1;
        counterX = 0;
        counterO = 0;
        
        for(int row = 2; row >= 0; row--){
            x++;
            for(int col = y; col <= x; col++){

                if(stateArray[row][col] == PLAYER_X){
                    counterX++;
                }else if(stateArray[row][col] == PLAYER_O){
                    counterO++;
                }
                y++;
            }
        }
        
        if(counterX > 0 && counterO == 0){
        	xTotalCount++;
        }else if(counterO > 0 && counterX == 0){
        	oTotalCount++;
        }    
    }

}//end class
	
	
	
	
	
	
	
	
	

