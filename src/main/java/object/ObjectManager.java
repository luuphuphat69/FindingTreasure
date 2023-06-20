/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Chest;
import com.captainhook.findingtreasure.Game;
import entity.Coin;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import usage.LoadSave;
import static usage.LoadSave.GetSpriteAtlas;

/**
 *
 * @author luuph
 */
public class ObjectManager {
    private Chest chest;
    private Coin coin;
    
    public ObjectManager(Chest chest, Coin coin) {
        this.chest = chest;
        this.coin = coin;
    }
    public static int[][] getLevelData() {

        BufferedImage levelImg = GetSpriteAtlas("level/" + LoadSave.LEVEL_ONE_DATA);
        int[][] levelData = new int[levelImg.getHeight()][levelImg.getWidth()];

        for (int j = 0; j < levelImg.getHeight(); j++) {
            for (int i = 0; i < levelImg.getWidth(); i++) {

                // Lấy giá trị color tại vị trí x, y
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getGreen();

                // 60: index của chest
//                if (value == 60) {
//                    levelData[j][i] = value;
//                }
                    levelData[j][i] = value;
            }
        }
        return levelData;
    }
    
    public void draw(Graphics g, Chest chest, Coin coin) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WITDH; i++) {
                int index = chest.getIndex(i, j);
                int _index = coin.getIndex(i, j);
                if(index == 60){
                    chest.setHitBoxDirection(Game.TILES_SIZE * i, j * Game.TILES_SIZE);
                    g.drawImage(chest.animation[0], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
                if(_index == 123){
                    coin.setHitBoxDirection(Game.TILES_SIZE * i, Game.TILES_SIZE * j);
                    g.drawImage(coin.animation[0], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }
    }
}
