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
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Ravi
 */
public abstract class GameObject{
    
    protected Spatial spatial;
    protected Material material;
    
    
    /**
     * Constructor untuk membuat sebuah objek diam
     * @param assetManager
     * @param name nama objek
     * @param path alamat objek
     */
    public GameObject(AssetManager assetManager,String name, String path, float scale) {
        this.spatial=assetManager.loadModel(path);
        this.spatial.setName(name);
        this.spatial.setLocalScale(scale);
    }
    
  
    public abstract String getName();
    public abstract Spatial getSpatial();
    public abstract void setScale(float scale);
    
}
