package object;

import entity.Chest;
import com.captainhook.findingtreasure.Game;
import entity.Coin;
import entity.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import usage.Constant;
import usage.LoadSave;
import static usage.LoadSave.GetSpriteAtlas;

/**
 *
 * @author luuph
 */
public class ObjectManager {
    private Chest chest;
    private ArrayList<Coin> coins;
    private Player player1, player2;
    int  point = 0;
    
    public ObjectManager(Chest chest, ArrayList<Coin> coins, Player player1, Player player2) {
        this.chest = chest;
        this.coins = coins;
        this.player1 = player1;
        this.player2 = player2;
    }

    public ObjectManager() {
    }
    
    public static int[][] getLevelData() {

        BufferedImage levelImg = GetSpriteAtlas("level/" + LoadSave.LEVEL_ONE_DATA);
        int[][] levelData = new int[levelImg.getHeight()][levelImg.getWidth()];

        for (int j = 0; j < levelImg.getHeight(); j++) {
            for (int i = 0; i < levelImg.getWidth(); i++) {

                // Lấy giá trị color tại vị trí x, y
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getGreen();
                    levelData[j][i] = value;
            }
        }
        return levelData;
    }
    
    public void draw(Graphics g) {
        drawChest(g);
        drawCoins(g);
    }
    
    public void drawChest(Graphics g){
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WITDH; i++) {
                int index = chest.getIndex(i, j);
                if(index == 60){
                    chest.setHitBoxDirection(Game.TILES_SIZE * i, j * Game.TILES_SIZE);
                    chest.drawHitBox(g, index, index);
                    g.drawImage(chest.animation[0], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }
    }
    
    public static ArrayList<Coin> GetCoins(BufferedImage img) {
        ArrayList<Coin> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 123) {
                    list.add(new Coin(i * Game.TILES_SIZE, j * Game.TILES_SIZE, 0));
                }
            }
        }
        return list;
    }
    
    public void drawCoins(Graphics g){
        BufferedImage coinImg = LoadSave.GetSpriteAtlas(Constant.ObjectConst.getImagePath(Constant.ObjectConst.COIN, -1));
        for(Coin coin: coins){
            coin.drawHitBox(g, 0, 0);
            checkCoinTouched();
            if(!coin.isClaimed()){
                g.drawImage(coinImg, coin.getX(), coin.getY(), 25, 25, null);
            }
        }
    }
    
    public void checkCoinTouched() {
        int total = 0;
        for (Coin coin : coins) {
            if (!coin.isClaimed()) {
                if (coin.hitBox.intersects(player1.getHitbox())
                        || coin.hitBox.intersects(player2.getHitbox())) {
                    coin.setClaimed(true);
                    total++;
                    calculatePoint(total);
                }
            }
        }
    }
    
    public void calculatePoint(int total){
        point += total;
    }
    
    public void drawPoint(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
        g.drawString("Point: " + String.valueOf(point), 40, 60);
    }
}