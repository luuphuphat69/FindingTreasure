package usage;

import com.captainhook.findingtreasure.Game;
import entity.Chest;
import entity.Coin;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author luuph
 */
public class LoadSave {
    
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    
    public static BufferedImage[] GetAllLevels(){
        URL url = LoadSave.class.getResource("/levels");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        File[] files = file.listFiles();
        BufferedImage[] imgs = new BufferedImage[files.length];
        for(int i = 0; i < imgs.length; i++){
            try {
                imgs[i] = ImageIO.read(files[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }
    
    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value > 143) {
                    value = 36;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}