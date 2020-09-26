package com.sc.tank.strategy;

import com.sc.tank.Player;
import com.sc.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    public void fire(Player p);
}
