/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jme3.scene.Spatial;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Richard
 */
public class LandscapeController {

    private LinkedList listOfGrounds = new LinkedList();
    private Spatial landscape;

    public LinkedList getListOfGrounds() {
        return listOfGrounds;
    }

    public Spatial getLandscape() {
        return landscape;
    }

    public void updateGround(float tpf) {
        Iterator<Spatial> ground = listOfGrounds.iterator();
        while (ground.hasNext()) {
            Spatial next = ground.next();
            next.move(0, 0, -20 * tpf);
            if (next.getLocalTranslation().getZ() <= -100) {
                next.getLocalTranslation().setZ(next.getLocalTranslation().getZ() + 200);
            }
        }

    }

}
