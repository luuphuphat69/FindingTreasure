package object;

import entity.Chest;
import com.captainhook.findingtreasure.Game;
import entity.Coin;
import entity.Player;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.ArrayList;
import level.Level;
import usage.Constant;
import usage.HelpMethods;
import usage.LoadSave;
/**
 *
 * @author luuph
 */
public class ObjectManager {

    private Chest chest;
    private ArrayList<Coin> coins;
    private Player player1, player2;
    int  point1 = 0;
    int point2= 0;
    
    
    public ObjectManager() {
    }
    
    public ObjectManager(Chest chest, ArrayList<Coin> coins, Player player1, Player player2) {
        this.chest = chest;
        this.coins = coins;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void draw(Graphics g) throws URISyntaxException {
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
        //chest.drawHitBox(g, Chest.xDrawOffset, Chest.yDrawOffset);
        g.drawImage(chestImg , chest.x, chest.y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    } 
    
    public void drawCoins(Graphics g) throws URISyntaxException{
        BufferedImage coinImg = LoadSave.GetSpriteAtlas(Constant.ObjectConst.getImagePath(Constant.ObjectConst.COIN));
        for(Coin coin: coins){
            //coin.drawHitBox(g, 0, 0);
            checkCoinTouched();
            if(!coin.isClaimed()){
                g.drawImage(coinImg, coin.x, coin.y, 25, 25, null);
            }
        }
    }
    
    public void checkCoinTouched() throws URISyntaxException {
        int total1 = 0;
        int total2 = 0;
        for (Coin coin : coins) {
            if (!coin.isClaimed()) {
                if (coin.hitBox.intersects(player1.getHitbox())) {
                    coin.setClaimed(true);
                    Game.playSound(Constant.SoundConst.COIN_SOUND);
                    total1++;
                    calculatePoint(total1, 1);
                }
                if(coin.hitBox.intersects(player2.getHitbox())){
                    coin.setClaimed(true);
                    Game.playSound(Constant.SoundConst.COIN_SOUND);
                    total2++;
                    calculatePoint(total2, 2);
                }
            }
        }
    }
    
    public void calculatePoint(int total, int playerIndex){
        if(playerIndex == 1)
            point1 += total;
        else
            point2 += total;
    }
    
    public void drawPoint(Graphics g){
        BufferedImage img1 = LoadSave.GetSpriteAtlas("monster1/face1.png");
        BufferedImage img2 = LoadSave.GetSpriteAtlas("monster2/face2.png");
        
        drawPointForPlayers(g, img1, point1, 1);
        drawPointForPlayers(g, img2, point2, 2);
        
    }
    
    public void setPoint() {
        point1 = 0;
        point2 = 0;
    }
    
    public void drawPointForPlayers(Graphics g, BufferedImage img, int point, int playerIndex){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
            if(playerIndex == 1){
            g.drawString("Point: " + String.valueOf(point), 80, 80);
            g.drawImage(img, 40, 60, null);
        }else{
            g.drawString("Point: " + String.valueOf(point), 80, 100);
            g.drawImage(img, 40, 80, null);
        }
    }
    public void reset(){
        setPoint();
        for (Coin coin : coins) {
            if (coin.isClaimed()) {
                coin.setClaimed(false);
            }
        }
    }
}