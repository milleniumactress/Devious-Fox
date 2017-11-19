/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;

/**
 *
 * @author Ravi
 */
public abstract class Obstacle extends GameObject{
    
    protected Material material;
    
    public Obstacle(AssetManager assetManager, String name, String path, 
            float scale, float x, float y, float z, Material material) {
        super(assetManager, name, path,scale
        );
        this.material=material;
    }
    
}
