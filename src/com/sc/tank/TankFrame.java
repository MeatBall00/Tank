package com.sc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();//单例

    private Player myTank;

    private List<Explode> explodes;
    private List<Tank> tanks;
    private List<Bullet> bullets;

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;


    private TankFrame(){
        this.setTitle("tank war");
        this.setLocation(400,100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        //观察者模式
        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100,100, Dir.R, Group.GOOD);
        tanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for(int i=0; i<tankCount; i++){
            tanks.add(new Tank(100 + 50*i, 200, Dir.D ,Group.BAD));
        }
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);//传的是内存中的图上的画笔
        g.drawImage(offScreenImage, 0, 0, null);//画完之后，调用这个让显存的画笔画内存中的图，来实现双缓冲
    }

    public void add(Bullet bullet){
        this.bullets.add(bullet);
    }

    @Override
    public void paint(Graphics g) { //java的图形系统自动调用，需要重新绘制时会调用这个方法 Graphics g 这个参数被系统初始化 代表图形里面系统传递给你的一支画笔
        //画一个方块 填充  如何动起来：1.不停刷新窗口 2.矩形位置进行变化
//        g.fillRect(x, y,50,50);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets" + bullets.size(), 15, 50);
        g.drawString("enemies" + tanks.size(), 15, 70);
        g.drawString("explodes" + explodes.size(), 15, 90);
        g.setColor(c);

        myTank.paint(g);
        for(int i =0; i<tanks.size(); i++){
            if(!tanks.get(i).isLive()){
                tanks.remove(i);
            }else {
                tanks.get(i).paint(g);
            }

        }

        for(int i=0; i<bullets.size(); i++){
            for(int j = 0; j < tanks.size(); j++){
                bullets.get(i).collidesWithTank(tanks.get(j));
            }

            if(!bullets.get(i).isLive()){
                bullets.remove(i);
            }
            else {
                bullets.get(i).paint(g);
            }
        }

        for(int i =0; i<explodes.size(); i++){
            if(!explodes.get(i).isLive()){
                explodes.remove(i);
            }else {
                explodes.get(i).paint(g);
            }
        }

//        System.out.println("paint");
    }

    public void add(Explode explode) {
        this.explodes.add(explode);
    }

    //内聚：有一个类，属于这个类的职责的都放在这个类里
    //耦合：别的类访问这个类时，牵扯的比较多，耦合度就比较高
    //内部类：高内聚 低耦合 只用于上面那个addkeyListener方法的，因此写成内部类就行 用private
    //也可以用匿名内部类（在一个类里面写），不容易看，内部类方便访问包装类的局部变量
    //KeyAdapter帮助实现了keylistenr的接口，只需要重写想实现的就行，如果直接继承keylistener接口，它里面的方法 不需要的也需要实现
    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

}
