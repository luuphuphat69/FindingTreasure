/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usage;

import com.captainhook.findingtreasure.Game;

/**
 *
 * @author luuph
 */
public class CollisionDetection {
    
    public static boolean checkIfCollision(int x, int y, int width, int height, int [][] levelData){
        if(!isSolid(x, y, levelData)){
            if(!isSolid(x + width, y + height, levelData)){
                if(!isSolid(x + width, y, levelData)){
                    if(!isSolid(x, y + height, levelData)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isSolid(int x, int y, int[][] levelData){
        if(x < 0|| x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        
        int positionXInLevelData = x / Game.TILES_SIZE;
        int positionYInLevelData = y / Game.TILES_SIZE;
        
        int value = levelData[positionYInLevelData][positionXInLevelData];
        
        if(value == 1 || value == 4 || value == 9 || value == 17 || value == 25){
            return true;   
        }
        return false;
    }
}
