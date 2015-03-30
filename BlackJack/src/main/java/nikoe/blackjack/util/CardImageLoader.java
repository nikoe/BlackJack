/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;

/**
 *
 * @author Niko
 */
public class CardImageLoader {

    private int cardWidth;
    private int cardHeight;
    private static final String PATH = "images/cards.png";
    private Map<Rank, Map<Suit, Image>> imageMap;

    public CardImageLoader() {
        loadImages();
    }

    private void loadImages() {
        BufferedImage i;
        try {
            i = ImageIO.read(getClass().getClassLoader().getResourceAsStream(PATH));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        imageMap = new HashMap<Rank, Map<Suit, Image>>();

        int x, y = 0;

        cardWidth = i.getWidth() / 13;
        cardHeight = i.getHeight() / 4;

        for (Suit suit : Suit.values()) {
            x = 0;
            for (Rank rank : Rank.values()) {
                BufferedImage cardImage = i.getSubimage(x++ * cardWidth+1, y
                        * cardHeight+1, cardWidth-1, cardHeight-2);

                Map<Suit, Image> images = imageMap.get(rank);
                if (images == null) {
                    images = new HashMap<Suit, Image>();
                }
                images.put(suit, cardImage);
                imageMap.put(rank, images);
            }
            y++;
        }
    }
    
    public Image getCardImage(Rank rank, Suit suit) {
        return imageMap.get(rank).get(suit);
    }
}
