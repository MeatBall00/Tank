import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankFrame extends Frame {
    private Tank myTank;
    private Tank enemy;
    public TankFrame(){
        this.setTitle("tank war");
        this.setLocation(400,100);
        this.setSize(800,600);
        //观察者模式
        this.addKeyListener(new TankKeyListener());
        myTank = new Tank(100,100, Dir.R);
        enemy = new Tank(200,200,Dir.D);
    }

    @Override
    public void paint(Graphics g) { //java的图形系统自动调用，需要重新绘制时会调用这个方法 Graphics g 这个参数被系统初始化 代表图形里面系统传递给你的一支画笔
        //画一个方块 填充  如何动起来：1.不停刷新窗口 2.矩形位置进行变化
//        g.fillRect(x, y,50,50);
        myTank.paint(g);
        enemy.paint(g);
//        System.out.println("paint");
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
