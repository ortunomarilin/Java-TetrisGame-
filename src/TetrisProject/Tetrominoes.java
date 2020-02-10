// ===============================
// AUTHOR       : Marilin Ortuno
// CREATE DATE  : August 24, 2019
// CLASS NAME   : Tetrominoes.java
// PURPOSE      : This class is used to create new tretrominos and thier corresponsing sets for the purpose of rotating the tetromino pieces.  
//==================================



package TetrisProject;

import javafx.scene.paint.Color;


public class Tetrominoes {
    
    private Tetromino[] tetrominoes = new Tetromino[7]; 
    private Color[] colors = {Color.CYAN, Color.YELLOW, Color.web("#fe00f6"), Color.BLUE, Color.ORANGE,  Color.web("#0bff01"), Color.RED};
    
    
    
    public Tetrominoes(int x, int y)
    {
        generateTetrominoes(x, y);
    }
    
    //Will return a random Tetromino Set.
    public Tetromino getRandomTetrominoSet(int random)
    {
        return tetrominoes[random];
    }

    //Store different Tetromino objects into the tetromino array. 
    //Each Tetromino will have its x,y,Color, and tetrominoSetMatrixes 
    public void generateTetrominoes(int x, int y)     
    {
        for(int index = 0; index < tetrominoes.length; index++)
        {
            tetrominoes[index] = new Tetromino(x, y, colors[index], getTetrominoSetMatrix(index));
        }
    }

    
    public int[][][] getTetrominoSetMatrix(int index)  
    {
        int[][][]matrix = null;
        
        switch (index) {
            //Cyan
            case 0:
            {
                matrix = new int[][][]{
                  { {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
                  },
                  { {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
                  },

                };
                break;
            }
            //Yellow
            case 1:
            {
                matrix = new int[][][]{
                  { {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  }, 
                };
                break;
            }
            
            //Violet
            case 2:
            {
                matrix = new int[][][]{
                  { {0, 1, 0, 0},
                    {1, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  }, 
                  { {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 0, 0},
                    {1, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  }
                };
                break;
            }
            
            //Blue
            case 3:
            {
                matrix = new int[][][]{
                  { {1, 0, 0, 0},
                    {1, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 0, 0},
                    {1, 1, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                };
                break;
            }
            
            //Orange
            case 4:
            {
                matrix = new int[][][]{
                  { {0, 0, 1, 0},
                    {1, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
                  }, 
                  { {0, 0, 0, 0},
                    {1, 1, 1, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  },

                };
                break;
            }
            //Green
            case 5:
            {
                matrix = new int[][][]{
                  { {0, 1, 1, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 0, 0},
                    {0, 1, 1, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {1, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  }, 
                };
                break;
            }
            //Red
            case 6:
            {
                matrix = new int[][][]{
                  { {1, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 1, 0},
                    {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
                  },
                  { {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}
                  }, 
                };
                break;
            }
        }
            
        return matrix;
  
    }
  
    
}
