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
import com.jme3.scene.Spatial;

/**
 *
 * @author Ravi
 */
public class Enemy extends Obstacle{
    
    protected Material material;
    private float x,y,z;
    
    public Enemy(AssetManager assetManager, String name, String path, 
            float scale, float x, float y, float z, 
            float vx, float vy, float vz, Material material) {
        super(assetManager, name, path,scale, x, y, z, material);
        this.material=material;
    }

    @Override
    public String getName() {
        return this.spatial.getName();
    }

    @Override
    public Spatial getSpatial() {
       return this.spatial;
    }

    @Override
    public void setScale(float scale) {
        this.spatial.setLocalScale(scale);
    }

    @Override
    public void setLocation(float x, float y, float z) {
       this.setLocation(x, y, z);
    }
    
    
    
}
