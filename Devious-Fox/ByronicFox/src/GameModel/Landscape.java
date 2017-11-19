/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;

/**
 *
 * @author Ravi
 */
public class Landscape extends GameObject {
    
    protected float scale;
    
    public Landscape(AssetManager assetManager, String name, String path, float scale) {
        super(assetManager, name, path,scale);
        this.scale=scale;
    }
    
    /**
     * getter nama spatial
     * @return nama spatial 
     */
    @Override
    public String getName() {
        return this.spatial.getName();
    }
    
    /**
     * getter spatial
     * @return spatial landscape
     */
    @Override
    public Spatial getSpatial() {
        return this.spatial;
    }
    
    /**
     * setter ukuran spatial
     * @param scale ukuran 
     */
    @Override
    public void setScale(float scale) {
       this.spatial.setLocalScale(scale);
    }

   
    
}
