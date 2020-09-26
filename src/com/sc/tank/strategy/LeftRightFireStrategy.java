package com.sc.tank.strategy;

import com.sc.tank.*;

public class LeftRightFireStrategy implements FireStrategy{
    @Override
    public void fire(Player p) {
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletD.getWidth()/2;
        int by = p.getY() +ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletD.getHeight()/2;

        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, Dir.L, p.getGroup()));
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, Dir.R, p.getGroup()));
    }
}
