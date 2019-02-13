

import java.util.Arrays;

public class ArrayState {
    private int PLAYER_X = 1;
    private int PLAYER_O = 2;
    
    //State1/Layer 1 possibilities-------------------------------------------------------------------------------------------
            
    		//unique
    		int state1Option1[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
            };
            
    		//unique
            int state1Option2[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state1Option3[][] = {
            {0, 0, PLAYER_O},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state1Option4[][] = {
            {0, 0, 0},
            {PLAYER_O, 0, 0},
            {0, 0, 0}
            };
           
            //unique
            int state1Option5[][] = {
            {0, 0, 0},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state1Option6[][] = {
            {0, 0, 0},
            {0, 0, PLAYER_O},
            {0, 0, 0}
            };
            
            //eliminate
            int state1Option7[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {PLAYER_O, 0, 0}
            };
            
            //eliminate
            int state1Option8[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {0, PLAYER_O, 0}
            };
            
            //eliminate
            int state1Option9[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, PLAYER_O}
            };
            
            //State2/Layer2 possibilities-------------------------------------------------------------------------------------------
            //the following 8 possibilities are children of State1Option1
            
            //unique
            int state2Option1[][] = {
            {PLAYER_O, PLAYER_X, 0},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option2[][] = {
            {PLAYER_O, 0, PLAYER_X},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option3[][] = {
            {PLAYER_O, 0, 0},
            {PLAYER_X, 0, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option4[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option5[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, PLAYER_X},
            {0, 0, 0}
            };
           
            //eliminate
            int state2Option6[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {PLAYER_X, 0, 0}
            };
            
            //eliminate
            int state2Option7[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {0, PLAYER_X, 0}
            };
            
            //unique
            int state2Option8[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {0, 0, PLAYER_X}
            };
            
            //These next 8 options are children of State1Option2
            
            //unique
            int state2Option9[][] = {
            {PLAYER_X, PLAYER_O, 0},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option10[][] = {
            {0, PLAYER_O, PLAYER_X},
            {0, 0, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option11[][] = {
            {0, PLAYER_O, 0},
            {PLAYER_X, 0, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option12[][] = {
            {0, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option13[][] = {
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_X},
            {0, 0, 0}
            };
            
            //unique
            int state2Option14[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {PLAYER_X, 0, 0}
            };
            
            //unique
            int state2Option15[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {0, PLAYER_X, 0}
            };
            
            //eliminate
            int state2Option16[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {0, 0, PLAYER_X}
            };
            
            //the following 8 options are children of State1Option3
            
            //unique
            int state2Option17[][] = {
            {PLAYER_X, 0, 0},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //unique
            int state2Option18[][] = {
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option19[][] = {
            {0, 0, PLAYER_X},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option20[][] = {
            {0, 0, 0},
            {PLAYER_X, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option21[][] = {
            {0, 0, 0},
            {0, PLAYER_O, PLAYER_X},
            {0, 0, 0}
            };
            
            //eliminate
            int state2Option22[][] = {
            {0, 0, 0},
            {0, PLAYER_O, 0},
            {PLAYER_X, 0, 0}
            };
            
            //eliminate
            int state2Option23[][] = {
            {0, 0, 0},
            {0, PLAYER_O, 0},
            {0, PLAYER_X, 0}
            };
            
            //eliminate
            int state2Option24[][] = {
            {0, 0, 0},
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_X}
            };
            
            //State3 possibilities-------------------------------------------------------------------------------------------
            
            /**State3 has many unique possibilities, therefore mostly non-unique tables are included
            only non-unique grids are needed for elimination
            */
            
            //children of state2Option1:  
            //unique
            int state3Option01[][] = {
            {PLAYER_O, PLAYER_X, 0},
            {0,0,0},
            {PLAYER_O,0,0}
            };
            
            //unique
            int state3Option02[][] = {
            {PLAYER_O, PLAYER_X, 0},
            {0,0,PLAYER_O},
            {0,0,0}		
            };
  
            //unique
            int state3Option03[][] = {
            {PLAYER_O, PLAYER_X, 0},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //children of state2Option4:           
            //unique
            int state3Option1[][] = {
            {PLAYER_O, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option2[][] = {
            {PLAYER_O, 0, PLAYER_O},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option3[][] = {
            {PLAYER_O, 0, 0},
            {PLAYER_O, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option4[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, PLAYER_O},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option5[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, 0},
            {PLAYER_O, 0, 0}
            };
            
            //eliminate
            int state3Option6[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0}
            };
            
            //unique
            int state3Option7[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, 0},
            {0, 0, PLAYER_O}
            };
                       
            //children of state2Option18:          
            //eliminate
            int state3Option12[][] = {
            {0, PLAYER_X, PLAYER_O},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option13[][] = {
            {0, PLAYER_X, 0},
            {PLAYER_O, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option14[][] = {
            {0, PLAYER_X, 0},
            {0, PLAYER_O, PLAYER_O},
            {0, 0, 0}
            };
            
            //unique
            int state3Option15[][] = {
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0},
            {PLAYER_O, 0, 0}
            };
            
            //unique
            int state3Option16[][] = {
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0},
            {0, PLAYER_O, 0}
            };
            
            //eliminate
            int state3Option17[][] = {
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_O}
            };
                            
            //children of state2Option17:
            
            //unique
            int state3Option21[][] = {
            {PLAYER_X, PLAYER_O, 0},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option22[][] = {
            {PLAYER_X, 0, PLAYER_O},
            {0, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option23[][] = {
            {PLAYER_X, 0, 0},
            {PLAYER_O, PLAYER_O, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option24[][] = {
            {PLAYER_X, 0, 0},
            {0, PLAYER_O, PLAYER_O},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option25[][] = {
            {PLAYER_X, 0, 0},
            {0, PLAYER_O, 0},
            {PLAYER_O, 0, 0}
            };
            
            //eliminate
            int state3Option26[][] = {
            {PLAYER_X, 0, 0},
            {0, PLAYER_O, 0},
            {0, PLAYER_O, 0}
            };
            
            //unique
            int state3Option27[][] = {
            {PLAYER_X, 0, 0},
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_O}
            };
            
            //children of state2Option12:
            
            //unique
            int state3Option31[][] = {
            {PLAYER_O, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option32[][] = {
            {0, PLAYER_O, PLAYER_O},
            {0, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //unique
            int state3Option33[][] = {
            {0, PLAYER_O, 0},
            {PLAYER_O, PLAYER_X, 0},
            {0, 0, 0}
            };
            
            //eliminate
            int state3Option34[][] = {
            {0, PLAYER_O, 0},
            {0, PLAYER_X, PLAYER_O},
            {0, 0, 0}
            };
            
            //unique
            int state3Option35[][] = {
            {0, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {PLAYER_O, 0, 0}
            };
            
            //unique
            int state3Option36[][] = {
            {0, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {0, PLAYER_O, 0}
            };
            
            //eliminate
            int state3Option37[][] = {
            {0, PLAYER_O, 0},
            {0, PLAYER_X, 0},
            {0, 0, PLAYER_O}
            };
            
            //children of state2Option15
            
            //unique
            int state3Option41[][] = {
            {PLAYER_O, PLAYER_O, 0},
            {0, 0, 0},
            {0, PLAYER_X, 0}
            };
            
            //eliminate
            int state3Option42[][] = {
            {0, PLAYER_O, PLAYER_O},
            {0, 0, 0},
            {0, PLAYER_X, 0}
            };
            
            //unique
            int state3Option43[][] = {
            {0, PLAYER_O, 0},
            {PLAYER_O, 0, 0},
            {0, PLAYER_X, 0}
            };
            
            //unique
            int state3Option44[][] = {
            {0, 0, 0},
            {0, PLAYER_O, PLAYER_O},
            {0, PLAYER_X, 0}
            };
            
            //eliminate
            int state3Option45[][] = {
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_O},
            {0, PLAYER_X, 0}
            };
            
            //unique
            int state3Option46[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {PLAYER_O, PLAYER_X, 0}
            };
            
            //eliminate
            int state3Option47[][] = {
            {0, PLAYER_O, 0},
            {0, 0, 0},
            {0, PLAYER_X, PLAYER_O}
            };
            
            //children of state2Option8
            
            //unique
            int state3Option48[][] = {
            {PLAYER_O, PLAYER_O, 0},
            {0, 0, 0},
            {0, 0, PLAYER_X}
            };
            
            //unique
            int state3Option49[][] = {
            {PLAYER_O, 0, PLAYER_O},
            {0, 0, 0},
            {0, 0, PLAYER_X}
            };
            
            //eliminate
            int state3Option50[][] = {
            {PLAYER_O, 0, 0},
            {PLAYER_O, 0, 0},
            {0, 0, PLAYER_X}
            };
            
            //unique
            int state3Option51[][] = {
            {PLAYER_O, 0, 0},
            {0, PLAYER_O, 0},
            {0, 0, PLAYER_X}
            };
            
            //unique
            int state3Option52[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, PLAYER_O},
            {0, 0, PLAYER_X}
            };
            
            //eliminate
            int state3Option53[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {PLAYER_O, 0, PLAYER_X}
            };
            
            //eliminate
            int state3Option54[][] = {
            {PLAYER_O, 0, 0},
            {0, 0, 0},
            {0, PLAYER_O, PLAYER_X}
            };
    
       
    //when this function returns true it means the state passed to this function is equivalent to 
    //a state that has already been stored in the tree, so no need to store this one
    public boolean stateExists(int[][]stateOfNode)
    {    
        boolean exists = false;
        
            //elimination of states layer 1 -----------------------------------------------------------------------------------------------------------------
        	
        	//eliminate equivalent corner states
            if(Arrays.deepEquals(stateOfNode, state1Option3) || Arrays.deepEquals(stateOfNode, state1Option7) || Arrays.deepEquals(stateOfNode, state1Option9))
            {
                exists = true;
            }
            
            //eliminate equivalent middle side states
            if(Arrays.deepEquals(stateOfNode, state1Option4) || Arrays.deepEquals(stateOfNode, state1Option6) || Arrays.deepEquals(stateOfNode, state1Option8))
            {
                exists = true;
            }
            
            
            //elimination of states layer 2 ----------------------------------------------------------------------------------------------------------------------
           
            //eliminate equivalent states of children of state1Option1
            if(Arrays.deepEquals(stateOfNode, state2Option3) || Arrays.deepEquals(stateOfNode, state2Option6) || Arrays.deepEquals(stateOfNode, state2Option7))
            {
                exists = true;
            }
            
            //eliminate equivalent states of children of state1Option2
            if(Arrays.deepEquals(stateOfNode, state2Option10) || Arrays.deepEquals(stateOfNode, state2Option13) || Arrays.deepEquals(stateOfNode, state2Option16))
            {
                exists = true;
            }
            
            //eliminate equivalent states of children of state1Option3
            //eliminate middle-corner states
            if(Arrays.deepEquals(stateOfNode, state2Option19) || Arrays.deepEquals(stateOfNode, state2Option22) || Arrays.deepEquals(stateOfNode, state2Option24))
            {
                exists = true;
            }
            //eliminate middle-side states
            if(Arrays.deepEquals(stateOfNode, state2Option20) || Arrays.deepEquals(stateOfNode, state2Option21) || Arrays.deepEquals(stateOfNode, state2Option23))
            {
                exists = true;
            }

             
            
            //elimination of states layer 3 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            
            //eliminate equivalent children of state2Option4
            if(Arrays.deepEquals(stateOfNode, state3Option3) || Arrays.deepEquals(stateOfNode, state3Option5) || Arrays.deepEquals(stateOfNode, state3Option6))
            {
                exists = true;
            }
            
            //eliminate equivalent children of state2Option18
            if(Arrays.deepEquals(stateOfNode, state3Option12) || Arrays.deepEquals(stateOfNode, state3Option14) || Arrays.deepEquals(stateOfNode, state3Option17))
            {
                exists = true;
            }
            
            //eliminate equivalent children of state2Option17
            if(Arrays.deepEquals(stateOfNode, state3Option23) || Arrays.deepEquals(stateOfNode, state3Option25) || Arrays.deepEquals(stateOfNode, state3Option26))
            {
                exists = true;
            }
            
            //eliminate equivalent states of children of state2Option12
            if(Arrays.deepEquals(stateOfNode, state3Option32) || Arrays.deepEquals(stateOfNode, state3Option34) || Arrays.deepEquals(stateOfNode, state3Option37))
            {
                exists = true;
            }
            
            //eliminate equivalent states of children of state2Option15
            if(Arrays.deepEquals(stateOfNode, state3Option42) || Arrays.deepEquals(stateOfNode, state3Option45) || Arrays.deepEquals(stateOfNode, state3Option47))
            {
                exists = true;
            }
            
            //eliminate equivalent states of children of state2Option8
            if(Arrays.deepEquals(stateOfNode, state3Option50) || Arrays.deepEquals(stateOfNode, state3Option53) || Arrays.deepEquals(stateOfNode, state3Option54))
            {
                exists = true;
            }
            
        return exists;
    }  
}