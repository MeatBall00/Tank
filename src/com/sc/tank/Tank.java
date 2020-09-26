package com.sc.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.Random;

public class Tank extends AbstractGameObject {
    public static final int SPEED = 3;
    private int x , y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = true;
    private boolean live = true;
    private Group group;

    private int width, height;
    private int oldX, oldY;

    private Rectangle rect;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        this.oldX = x;
        this.oldY = y;

        this.width = ResourceMgr.badTankU.getWidth();
        this.height = ResourceMgr.badTankU.getHeight();

        this.rect = new Rectangle(x, y, width, height);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
//        g.fillRect(x, y, 50,50);
        if(!this.isLive())
            return;
        switch (dir){
            case L :
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U :
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R :
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D :
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }

        Color old = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(x,y,rect.width,rect.height);
        g.setColor(old);

        move();
        rect.x = x;
        rect.y = y;

    }

    private void move() {
        if(!moving)
            return;
        oldX = x;
        oldY = y;
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

        boundsCheck();

        randomDir();
        if(r.nextInt(100) > 90)
            fire();
    }

    private Random r = new Random();
    private void randomDir() {
        if(r.nextInt(100) > 90){
            this.dir = Dir.randomDir();//0 1 2 3
        }
    }

    private void boundsCheck(){
        if(x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT){
            this.back();
        }
    }

    public void back() {
        x = oldX;
        y = oldY;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT :
                bL = false;
                break;
            case KeyEvent.VK_UP :
                bU = false;
                break;
            case KeyEvent.VK_RIGHT :
                bR = false;
                break;
            case KeyEvent.VK_DOWN :
                bD = false;
                break;
            case KeyEvent.VK_CONTROL :
                fire();
                break;
        }
    }

    private void fire() {
        int bx = x +ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletD.getWidth()/2;
        int by = y +ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletD.getHeight()/2;
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, dir, group));
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.getGm().add(new Explode(x, y));

    }

    public Rectangle getRect() {
        return rect;
    }
}
