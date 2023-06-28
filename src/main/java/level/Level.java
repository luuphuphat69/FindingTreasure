package level;

import entity.Chest;
import entity.Coin;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.ObjectManager;
import usage.HelpMethods;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Level {
    private int[][] lvlData;
    private ArrayList<Coin> coins;
    private BufferedImage img;
    private Chest chest;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createCoins();
        createChest();
    }

    public int getTileIndex(int x, int y) {
        return lvlData[y][x];
    }

    public BufferedImage getImg() {
        return img;
    }
    
    private void createLevelData(){
        lvlData = LoadSave.GetLevelData(img);
    }
    
    private void createCoins(){
        coins = HelpMethods.GetCoins(img);
    }
    
    private void createChest(){
        chest = HelpMethods.GetChest(img);
    }
   
    public ArrayList<Coin> getCoins(){
        return coins;
    }
    
    public Chest getChest(){
        return chest;
    }
    
    public int[][] getLevelData() {
        return lvlData;
    }
}
