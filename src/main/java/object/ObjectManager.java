package object;

import entity.Chest;
import com.captainhook.findingtreasure.Game;
import entity.Coin;
import entity.Player;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import level.Level;
import usage.Constant;
import usage.LoadSave;
/**
 *
 * @author luuph
 */
public class ObjectManager {

    private Chest chest;
    private ArrayList<Coin> coins;
    private Player player1, player2;
    int  point = 0;
    
    
    public ObjectManager() {
    }
    
    public ObjectManager(Chest chest, ArrayList<Coin> coins, Player player1, Player player2) {
        this.chest = chest;
        this.coins = coins;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void draw(Graphics g) {
        drawChest(g);
        drawCoins(g);
    }
    
    public void loadObjects(Level level){
        coins = level.getCoins();
        chest = level.getChest();
        setChest(chest);
    }
    
    public void setChest(Chest chest){
        this.chest = chest;
    }
    
    public Chest getChest(){
        return this.chest;
    }
        
    public void drawChest(Graphics g) {
        BufferedImage chestImg = LoadSave.GetSpriteAtlas(Constant.ObjectConst.getImagePath(Constant.ObjectConst.CHEST));
        chest.drawHitBox(g, Chest.xDrawOffset, Chest.yDrawOffset);
        g.drawImage(chestImg , chest.x, chest.y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    } 
    
    public void drawCoins(Graphics g){
        BufferedImage coinImg = LoadSave.GetSpriteAtlas(Constant.ObjectConst.getImagePath(Constant.ObjectConst.COIN));
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

    public void setPoint() {
        point = 0;
    }
}