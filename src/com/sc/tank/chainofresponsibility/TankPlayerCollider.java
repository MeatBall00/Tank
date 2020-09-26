package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;
import com.sc.tank.Dir;
import com.sc.tank.Player;
import com.sc.tank.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TankPlayerCollider implements Collider{
    Map<Tank, Dir> tankDirMap = new HashMap<>(); //坦克，player以什么方向撞上的坦克

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Player && go2 instanceof Tank){
            Player p = (Player) go1;
            Tank t = (Tank) go2;
            if(t.isLive() && p.isLive()){
                if(t.getRect().intersects(p.getRect())){
                    if(t.getRect().intersects(p.getNextRect())){
                        p.stop();
                    }
                    t.back();
                }
            }
            return  false;
        }
        return true;
    }
}
