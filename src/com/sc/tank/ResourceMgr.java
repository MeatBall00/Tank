package com.sc.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class for managing pictures
 */
public enum ResourceMgr {
    INSTANCE;


    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD; // good tank images
    public static BufferedImage badTankL, badTankU, badTankR, badTankD; //bad tank images

    public static BufferedImage bulletL, bulletU, bulletR, bulletD; // bullet images
    public static BufferedImage[] explodes = new BufferedImage[16];

    //constructor
    static {
        System.out.println("开始初始化");
        try {
            //get pictures for tank_left tank_right tank_up tank_down
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            //get pictures for bullet_left bullet_right bullet_up bullet_down
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            //explosion picutre
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  BufferedImage getGoodTankL() {
        return goodTankL;
    }

    public  BufferedImage getGoodTankU() {
        return goodTankU;
    }

    public  BufferedImage getGoodTankR() {
        return goodTankR;
    }

    public  BufferedImage getGoodTankD() {
        return goodTankD;
    }

    public  BufferedImage getBadTankL() {
        return badTankL;
    }

    public  BufferedImage getBadTankU() {
        return badTankU;
    }

    public  BufferedImage getBadTankR() {
        return badTankR;
    }

    public  BufferedImage getBadTankD() {
        return badTankD;
    }

    public  BufferedImage getBulletL() {
        return bulletL;
    }

    public  BufferedImage getBulletU() {
        return bulletU;
    }

    public  BufferedImage getBulletR() {
        return bulletR;
    }

    public  BufferedImage getBulletD() {
        return bulletD;
    }

    public  BufferedImage[] getExplodes() {
        return explodes;
    }
}

