package com.sc.tank;

import com.sc.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private Player myTank;
    ColliderChain chain = new ColliderChain();
    List<AbstractGameObject> objects;

    public GameModel() {
        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100,100, Dir.R, Group.GOOD);

        objects = new ArrayList<>();
        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for(int i=0; i<tankCount; i++){
            this.add(new Tank(100 + 100*i, 500, Dir.D ,Group.BAD));
        }
        this.add(myTank);
        this.add(new Wall(300, 200, 400, 20));
    }

    public void add(AbstractGameObject go){
        objects.add(go);
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("bullets" + bullets.size(), 15, 50);
//        g.drawString("enemies" + tanks.size(), 15, 70);
//        g.drawString("explodes" + explodes.size(), 15, 90);
        g.setColor(c);

        for(int i = 0; i < objects.size(); i++){
            AbstractGameObject object = objects.get(i);
            if(!object.isLive()){
                objects.remove(object);
                break;
            }
        }


        for(int i = 0; i < objects.size(); i++){
            AbstractGameObject go1 = objects.get(i);
            for(int j = 0; j < objects.size(); j++){
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }

            if(objects.get(i).isLive()){
                objects.get(i).paint(g);
            }
        }
//        myTank.paint(g);


    }

    public Player getMyTank(){
        return myTank;
    }

}
