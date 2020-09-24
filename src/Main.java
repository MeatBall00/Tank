import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        //TankFrame继承自Frame，可以重写Frame的方法，实现画图
        TankFrame tf = new TankFrame(); //窗口
        tf.setVisible(true);
        //死循环
        for(;;){
            try {
                Thread.sleep(25);//睡多少毫秒（1000毫秒/25）每秒40帧
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每25毫秒调用repaint
            tf.repaint();//会调用到paint方法
        }

    }


}
