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
        BufferedImage img = LoadSave.GetSpriteAtlas("tiles/spritesheet.png");
        
        // 64: 8x8 tiles trong Tiles.png
        levelTile = new BufferedImage[144];
        int index = 0;
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 12; i++) {
                levelTile[index] = img.getSubimage(i * 48 , j * 48, 45, 48);
                index++;
            }
        }
    }
    
    // 1 pixel có 3 màu (R, G, B)
    // Mỗi pixel trong ảnh của Level, màu R sẽ dùng để chứa index 1 tiles trong
    // array Tiles
    public static int[][] getLevelData() {   
        
        BufferedImage levelImg = GetSpriteAtlas("level/" + LoadSave.LEVEL_ONE_DATA);
        int[][] levelData = new int[levelImg.getHeight()][levelImg.getWidth()]; 
        
        for (int j = 0; j < levelImg.getHeight(); j++) {
            for (int i = 0; i < levelImg.getWidth(); i++) {
                
                // Lấy giá trị color tại vị trí x, y
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getRed();
                
                // 144 : tổng tiles trong tiles array 12x12
                /*Trong tiles array, các tile được thêm vào mảng lần lượt
                 từ trái qua phải, bắt đầu từ vị trí số 0
                */
                if (value > 143) {
                    // 36: ảnh nền xanh
                    value = 143;
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
                // Vẽ nền xanh dương, tile số 36
                g.drawImage(levelTile[36], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                g.drawImage(levelTile[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }
}
