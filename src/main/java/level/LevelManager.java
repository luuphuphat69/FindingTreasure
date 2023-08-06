package level;

import com.captainhook.findingtreasure.Game;
import entity.Button;
import entity.Menu;
import entity.Player;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.ObjectManager;
import usage.Constant;
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
    public Menu menu;
    public Button menuBtn, restartBtn, continueBtn;
    public boolean isPaused = false;
    public BufferedImage listButtonImg = LoadSave.GetSpriteAtlas(Constant.UI_Const.BUTTON_UI);
    
    public LevelManager() {
        importOutsideTiles();
        listLevel = new ArrayList<>();
        buildAllLevels();
        
        initBtns();
        menu = new Menu((int)(Game.GAME_HEIGHT / 2) + 50,50, 400, 400);
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
        BufferedImage pauseButtonImg = listButtonImg.getSubimage(5 * 16, 0 * 16, 15,15);
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WITDH; i++) {
                int index = getCurrentLevel().getTileIndex(i, j);
                // Vẽ nền xanh dương, tile số 36
                g.drawImage(levelTile[36], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                g.drawImage(levelTile[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                if(index == 1){
                    menuBtn = new Button(960, j, 15, 15, pauseButtonImg);
                    menuBtn.draw(g, i * Game.TILES_SIZE, j * Game.TILES_SIZE);
                }
            }
        }
    }
    
    public void drawAllButton(Graphics g){
        restartBtn.draw(g);
        continueBtn.draw(g);
    }

    public void initBtns() {
        BufferedImage continueButtonImg = listButtonImg.getSubimage(1 * 16, 0 * 16, 15, 15);
        BufferedImage restartButtonImg = listButtonImg.getSubimage(3 * 16, 0 * 16, 15, 15);

        menuBtn = new Button();
        restartBtn = new Button((int) (Game.GAME_HEIGHT / 2) + 150, 350, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE), restartButtonImg);
        continueBtn = new Button((int) (Game.GAME_HEIGHT / 2) + 320, 350, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE), continueButtonImg);

        Rectangle restartBound = new Rectangle(restartBtn.getX(), restartBtn.getY(), restartBtn.getWidth(), restartBtn.getHeight());
        Rectangle continueBound = new Rectangle(continueBtn.getX(), continueBtn.getY(), continueBtn.getWidth(), continueBtn.getHeight());
        restartBtn.setBounds(restartBound);
        continueBtn.setBounds(continueBound);
    }
    
    public void restartPoint(){
        objectManager.setPoint();
    }
    
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuBtn)) {
            isPaused = !isPaused;
        }
        else if (isInBtn(e, restartBtn)) {
            isPaused = false;
            player1.setPlayerSpawn();
            player2.setPlayerSpawn();
            objectManager.reset();
        }
        else if (isInBtn(e, continueBtn)) {
            isPaused = false;
        }
    }
    
    private boolean isIn(MouseEvent e, Button b) {
        return b.getBounds().contains(e.getX(),0);
    }
    private boolean isInBtn(MouseEvent e, Button b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
