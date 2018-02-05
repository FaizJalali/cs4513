/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import model.Moveable.Gamer;
import model.Moveable.Monster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Key;
import model.Immoveable.Tile.Lock;

/**
 *
 * @author russe_000
 */
public class GameData {
    public static List<GameObject> gameObjects;
    public static List<GameObject> gamerInventory;
    public static Gamer gamer;
    public static Key redKey, yellowKey;
    public static Lock blueLock, yellowLock, socket;
    public static Boot fireBoot, iceBoot;
    public static int level;
    public static int time;
    public static int chipsLeft;
    private int timerCounter;

    public GameData() 
    {
        gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
        gamerInventory = Collections.synchronizedList(new ArrayList<GameObject>());
        
        // Level specific items
        gamer = new Gamer(650, 625);
        GameData.gameObjects.add(GameData.gamer);
        GameData.level = 1;
        GameData.time = 120;
        GameData.chipsLeft = 3;
        timerCounter = 0;
        
        //Level keys
        redKey = new Key(600, 100, LockType.RED);
        yellowKey = new Key(650, 100, LockType.YELLOW);
        gameObjects.add(redKey);
        gameObjects.add(yellowKey);
        
        //Level locks
        blueLock = new Lock(100, 100, LockType.BLUE);
        yellowLock = new Lock(100, 150, LockType.YELLOW);
        socket = new Lock(100, 200, LockType.SOCKET);
        gameObjects.add(blueLock);
        gameObjects.add(yellowLock);
        gameObjects.add(socket);
        
        //Level boots
        fireBoot = new Boot(900, 100, BootType.FIRE);
        iceBoot = new Boot(900, 150, BootType.ICE);
        gameObjects.add(fireBoot);
        gameObjects.add(iceBoot);
        
        //Level chips
        gameObjects.add(new Chip(300, 100));
        gameObjects.add(new Chip(350, 100));
        gameObjects.add(new Chip(300, 150));
    }
    
    public void resetGameData()
    {
        GameData.gamer = new Gamer(650, 625);
        GameData.gamer.update();
    }
    
    public void collectChip(){
        --chipsLeft;
    }
    
    public void update() 
    {
        if(GameData.time > 0)
        {
            if(timerCounter == 10){
                timerCounter = 0;
                GameData.time--;
            }
            else{
                timerCounter++;
            }
        }
        
        synchronized(gameObjects)
        {
            for(GameObject object: gameObjects)
            {
                if(object instanceof Monster)
                {
                    ((Monster)object).update();
                    
                    // ((Monster)object).findCollision();
                }
                else if(object instanceof Gamer){
                    ((Gamer)object).update();
                }
            }
        }
        
        ArrayList<GameObject> removeInventory = new ArrayList<>();
        synchronized(gamerInventory){
            for(GameObject object : gamerInventory){
                if(!object.isAlive()){
                    removeInventory.add(object);
                }             
            }
        }
        gamerInventory.removeAll(removeInventory);
                       
    }
}
