/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Graphics;


public class Chest extends Object{
    
    public static int xDrawOffset;
    public static int yDrawOffset;
    
    private int[][] levelData;

    public Chest() {
    }
    
    public Chest(int x, int y, int type){
        super(x, y, type);
        
        super.x = x;
        super.y = y;
        super.type = type;    
        initHitbox(35, 35);
        
        xDrawOffset = (int) (5 * Game.SCALE);
        yDrawOffset = (int) (4 * Game.SCALE);
    }
    
    public int getIndex(int x, int y){
        return levelData[y][x];
    }
    
    public void setLevelData(int [][] levelData){
        this.levelData = levelData;
    }
}