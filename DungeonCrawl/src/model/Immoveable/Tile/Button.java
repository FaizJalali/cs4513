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
import java.util.ArrayList;
import model.ButtonType;
import model.GameData;
import model.GameObject;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class Button extends Tile {
    public BufferedImage image;

    ButtonType type;
    ArrayList<GameObject> associatedObjects = new ArrayList<GameObject>();

    BufferedImage[] buttonImgs;
    public boolean pressed = false;

    public Button(float x, float y, ButtonType type, ArrayList<GameObject> associatedObjects) {
        super(x, y);
        this.type = type;
        
        this.associatedObjects.addAll(associatedObjects);
        
        showImages();
    }

    public void showImages() {
        try {
            buttonImgs = new BufferedImage[4];

            BufferedImage image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Green_Button.png");
            buttonImgs[0] = image;

            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Red_Button.png");
            buttonImgs[1] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Blue_Button.png");
            buttonImgs[2] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Brown_Button.png");
            buttonImgs[3] = image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D g) {  
        BufferedImage image;
        switch (type) {
            case GREEN:
                image = buttonImgs[0];
                break;
            case RED:
                image = buttonImgs[1];
                break;
            case BLUE:
                image = buttonImgs[2];
                break;
            default:
                image = buttonImgs[3];
            }
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.width, (int) super.height, null);

        //Draw Collision Box
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }
    
    public ButtonType getType(){
        return type;
    }
    
    @Override
    public void collide(GameObject O){
        if(!pressed){
            pressed = true;
            
            if(type == ButtonType.GREEN){
                if(O instanceof Gamer){
                    this.associatedObjects.forEach((o)-> setObject(o));
                }
            } 
            /*
            else if(type == ButtonType.BLUE) {
                if (O instanceof Gamer) {
                   this.associatedObjects.forEach((o)-> setObject(o));
                }
            }
            */
        }
    }

    public void setObject(GameObject object){
        int indexOfObject = GameData.gameObjects.indexOf(object);

        if(object instanceof ToggleWall){
            ((ToggleWall) object).setOpen(!((ToggleWall) object).isOpen());
            GameData.gameObjects.set(indexOfObject, object);
        }
    }
}
