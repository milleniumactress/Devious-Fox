/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.jme3.asset.AssetManager;

/**
 *
 * @author Ravi
 */
public abstract class Obstacle extends GameObject {
    
    public Obstacle(String name, String path, AssetManager assetManager, float x, float y, float z) {
        super(name, path, assetManager, x, y, z);
    }
    
    
}
