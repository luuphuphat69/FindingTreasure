/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package level;

import com.captainhook.findingtreasure.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import usage.LoadSave;
import static usage.LoadSave.GetSpriteAtlas;

/**
 *
 * @author luuph
 */
public class LevelManager {
    
    private Game game;
    public BufferedImage[] levelTile;
    private Level levelOne;

    public LevelManager() {
        importOutsideTiles();
        levelOne = new Level(getLevelData());
    }

    // Tiles array
    private void importOutsideTiles() {
        BufferedImage img = LoadSave.GetSpriteAtlas("tiles/Tiles.png");
        
        // 64: 8x8 tiles trong Tiles.png
        levelTile = new BufferedImage[64];
        int index = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                levelTile[index] = img.getSubimage(i * 32 , j* 32, 32, 32);
                index++;
            }
        }
    }
    
    // 1 pixel có 3 màu (R, G, B)
    // Mỗi pixel trong ảnh của Level, màu R sẽ dùng để chứa index 1 tiles trong
    // array Tiles
    public static int[][] getLevelData() {   
        
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WITDH]; 
        BufferedImage levelImg = GetSpriteAtlas("level/level1.png");
        
        for (int j = 0; j < levelImg.getHeight(); j++) {
            for (int i = 0; i < levelImg.getWidth(); i++) {
                
                // Lấy giá trị color tại vị trí x, y
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getRed();
                
                // 64 : tổng pixel trong sprite level 8x8
                if (value >= 64) {
                    value = 0;
                }
                levelData[j][i] = value;
            }
        }
        return levelData;
    }
    
    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WITDH; i++) {
                int index = levelOne.getTileIndex(i, j);
                g.drawImage(levelTile[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }
}
