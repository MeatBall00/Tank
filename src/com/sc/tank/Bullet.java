package com.sc.tank;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.BitSet;

public class Bullet extends AbstractGameObject {
    public static final int SPEED = 10; //速度
    private int x, y; //位置
    private Dir dir;//方向
    private Group group;
    private boolean live = true;
    private int w = ResourceMgr.bulletU.getWidth(), h = ResourceMgr.bulletU.getHeight();
    private Rectangle rect;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect = new Rectangle(x, y, w, h);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", live=" + live +
                ", w=" + w +
                ", h=" + h +
                ", rect=" + rect +
                '}';
    }

    public void paint(Graphics g) {

        switch (dir){
            case L :
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U :
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R :
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D :
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            }
        move();
//        Color old = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.drawRect(rect.x,rect.y,rect.width,rect.height);
//        g.setColor(old);
    }
    

    private void move() {
        switch (dir){
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        //update rect
        rect.x = x;
        rect.y = y;

        boundsCheck();
    }

    public void die(){
        this.setLive(false);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void boundsCheck(){
        if(x<0 || y<30 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            live = false;
        }
    }

    public Rectangle getRect() {
        return rect;
    }
}

