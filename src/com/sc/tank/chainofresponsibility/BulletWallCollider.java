package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;
import com.sc.tank.Bullet;
import com.sc.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Bullet && go2 instanceof Wall){
            Bullet b = (Bullet) go1;
            Wall w = (Wall) go2;
            if(b.isLive()){
                if(b.getRect().intersects(w.getRect())){
                    b.die();
                    return false;
                }
            }
        }
        else if(go1 instanceof Wall && go2 instanceof Bullet){
            collide(go2, go1);
        }
        return true;
    }
}
