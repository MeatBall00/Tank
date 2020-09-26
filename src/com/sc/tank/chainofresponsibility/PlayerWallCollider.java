package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;
import com.sc.tank.Player;
import com.sc.tank.Tank;
import com.sc.tank.Wall;

public class PlayerWallCollider implements Collider{

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Player && go2 instanceof Wall){
            Player p = (Player) go1;
            Wall w = (Wall) go2;
            if(p.isLive()){
                if(p.getRect().intersects(w.getRect())){
                    p.stop();
                }
            }
        }
        else if(go1 instanceof Wall && go2 instanceof Tank){
            collide(go2, go1);
        }
        return true;
    }
}
