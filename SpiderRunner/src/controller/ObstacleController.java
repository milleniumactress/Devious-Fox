/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jme3.scene.Spatial;
import models.*;

/**
 *
 * @author Richard
 */
public class ObstacleController {

    private Obstacle[] obstaclesList;

    public ObstacleController(Obstacle[] o) {
        obstaclesList = o;
    }

    public Obstacle[] getListOfGrounds() {
        return obstaclesList;
    }

    public void updateGround(float tpf) {
        while (true) {
            for (int i = 0; i < obstaclesList.length; i++) {
                Spatial newSpatial = obstaclesList[i].getSpatial();
                newSpatial.move(0, 0, -20 * tpf);
                if (newSpatial.getLocalTranslation().getZ() <= -5) {
                    newSpatial.getLocalTranslation().setZ(50);
                }
            }
        }

    }
}
