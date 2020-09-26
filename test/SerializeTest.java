import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class SerializeTest { //序列化测试，把物体写到硬盘上
    @Test
    public void testSave(){

        try {
            T t = new T();
            File f = new File("/Users/admin/Desktop/s.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLoad(){

        try {
            T t = new T();
            File f = new File("/Users/admin/Desktop/s.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            t = (T)(ois.readObject());
            Assertions.assertEquals(4,t.m);
            Assertions.assertEquals(0,t.n);
            Assertions.assertEquals(8,t.a.weight);

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//要对哪个类实现序列化，需要implements序列化接口
class T implements Serializable{
    int m = 4;
    transient int n = 8; //n不参与序列化 如密码
    Apple a = new Apple();
}
class Apple implements Serializable{
    int weight = 8;
}

