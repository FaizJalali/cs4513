/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.ButtonType;
import model.GameObject;

/**
 *
 * @author russe_000
 */
public class Button extends Tile {
    public BufferedImage image;
    
    public Button(float x, float y, ButtonType b) {
        super(x, y);
        
        try{
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Green_Button.png");
        }
        catch(Exception e){}
        
    }

    @Override
    public void render(Graphics2D g) {  
        g.drawImage(image, (int) super.x, (int)super.y, (int)super.height, (int)super.width, null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }
    
    @Override
     public void collide(GameObject O){
    
    }
}
