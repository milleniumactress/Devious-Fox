/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author Ravi
 */
public class Bird extends Obstacle {

    public Bird(String name, String path, AssetManager assetManager, float x, float y, float z) {
        super(name, path, assetManager, x, y, z);
        this.spatial=assetManager.loadModel(name);
    }

    @Override
    public void loadModels() {
        this.spatial=assetManager.loadModel(this.path);
    }

    @Override
    public void setMaterials(Material material) {
        this.spatial.setMaterial(material);
    }

    @Override
    public void setTransLation(float x, float y, float z) {
        this.spatial.setLocalTranslation(x, y, z);
    }

    @Override
    public void setName(String name) {
        this.spatial.setName(name);
    }

    @Override
    public String getName() {
        return this.spatial.getName();
    }
    
    public Spatial getSpatial(){
        return this.spatial;
    }
    
}
