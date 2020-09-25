package com.sc.tank.chainofresponsibility;

import com.sc.tank.AbstractGameObject;

public interface Collider {
    //返回true，chain继续 返回false chain终止
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);

}
