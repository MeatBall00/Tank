package com.sc.tank.strategy;

import com.sc.tank.*;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p){
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletD.getWidth()/2;
        int by = p.getY() +ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletD.getHeight()/2;

//        Dir[] dirs = Dir.values();
//        for(Dir d : dirs)
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, p.getDir(), p.getGroup()));
    }
}
