/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usage;

import com.captainhook.findingtreasure.Game;
import entity.Chest;
import entity.Coin;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author luuph
 */
public class HelpMethods {
    
    public static ArrayList<Coin> GetCoins(BufferedImage img) {
        ArrayList<Coin> listCoins = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 123) {
                    listCoins.add(new Coin(i * Game.TILES_SIZE, j * Game.TILES_SIZE, 0));
                }
            }
        }
        return listCoins;
    }
    
    public static Chest GetChest(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 60) {
                    return new Chest(i * Game.TILES_SIZE, j * Game.TILES_SIZE, 0);
                }
            }
        }
        return new Chest(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE, 0);
    }
}
