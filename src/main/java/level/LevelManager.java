package level;

import com.captainhook.findingtreasure.Game;
import entity.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.ObjectManager;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class LevelManager {
    
    public BufferedImage[] levelTile;
    public static ArrayList<Level> listLevel;
    public static int levelIndex = 0;
    public ObjectManager objectManager;
    public Player player1, player2;
    
    public LevelManager() {
        importOutsideTiles();
        listLevel = new ArrayList<>();
        buildAllLevels();
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
    
    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for (BufferedImage img : allLevels) {
            listLevel.add(new Level(img));
        }
    }
    
    public void loadNextLevel() {
        if (levelIndex >= listLevel.size()) {
            System.out.println("No more levels! Game Completed!");
            levelIndex = listLevel.size() - 1;
        }
        Level newLevel = listLevel.get(levelIndex);
        objectManager.loadObjects(newLevel);
        player1.loadLevelData(newLevel.getLevelData());
        player2.loadLevelData( newLevel.getLevelData());
    }

    public Level getCurrentLevel() {
        return listLevel.get(levelIndex);
    }

    public int getAmountOfLevels() {
        return listLevel.size();
    }
    
    public void getObjectManager(ObjectManager objectManager){
        this.objectManager = objectManager;
    }
    public void getPlayer(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    
    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WITDH; i++) {
                int index = getCurrentLevel().getTileIndex(i, j);
                // Vẽ nền xanh dương, tile số 36
                g.drawImage(levelTile[36], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                g.drawImage(levelTile[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }
    
    public void restartPoint(){
        objectManager.setPoint();
    }
}
