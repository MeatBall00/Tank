package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;
import com.sc.tank.Bullet;
import com.sc.tank.ResourceMgr;
import com.sc.tank.Tank;

import java.awt.*;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Bullet && go2 instanceof Tank){
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;
            if(!b.isLive() || !t.isLive()) return false;
            if(b.getGroup() == t.getGroup()) return true;

//        Rectangle rect = new Rectangle(x, y, w, h);
            Rectangle rectTank = t.getRect();
            if(b.getRect().intersects(rectTank)){
                b.die();
                t.die();
                return false;//不继续了
            }
        }
        else if(go1 instanceof Tank && go2 instanceof Bullet){//?不用吧
            return collide(go2, go1);
        }
        return true;
    }
}
