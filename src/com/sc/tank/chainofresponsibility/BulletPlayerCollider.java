package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;
import com.sc.tank.Bullet;
import com.sc.tank.Player;
import com.sc.tank.Tank;

import java.awt.*;

public class BulletPlayerCollider implements Collider{

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Bullet && go2 instanceof Player){
            Bullet b = (Bullet) go1;
            Player p = (Player) go2;
            if(b.getGroup() == p.getGroup()) return true;
            if(!b.isLive() || !p.isLive()) return false;

            if(b.getRect().intersects(p.getRect())){
                b.die();
                p.die();
                return false;//不继续了
            }
        }
        else if(go1 instanceof Tank && go2 instanceof Bullet){//?不用吧
            return collide(go2, go1);
        }
        return true;
    }
}
