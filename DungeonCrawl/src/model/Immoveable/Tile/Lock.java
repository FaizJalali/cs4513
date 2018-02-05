
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.LockType;

public class Lock extends Wall{
    private final int width = 32;
    private final int height = 32;
    
    public LockType color;
    
    public BufferedImage[] lockImg;
    public Lock(float x, float y, LockType k) {
        super(x, y);
        this.color = k;
        
        lockImg = new BufferedImage[5];
        
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Blue_Lock.png");
            lockImg[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Green_Lock.png");
            lockImg[1] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Red_Lock.png");
            lockImg[2] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Yellow_Lock.png");
            lockImg[3] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Socket.png");
            lockImg[4] = image;
        } catch(Exception e){
            e.printStackTrace();
        }
    };

    @Override
    public void render(Graphics g) {
        if(this.isAlive()){
            BufferedImage image;
            switch(color){
                case BLUE:
                    image = lockImg[0];
                    break;
                case GREEN:
                    image = lockImg[1];
                    break;
                case RED:
                    image = lockImg[2];
                    break;
                case SOCKET:
                    image = lockImg[4];
                    break;
                default:
                    image = lockImg[3];
            }
            g.drawImage(image, (int)super.x, (int)super.y, 50, 50, null);
        }
    }
}
