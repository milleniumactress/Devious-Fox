/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;

/**
 *
 * @author Ravi
 */
public class Spider extends MainCharacter {
    
    public Spider(String name, String path, AssetManager assetManager, float x, float y, float z) {
        super(name, path, assetManager, x, y, z);
        this.spatial=assetManager.loadModel(path);
    }

    @Override
    public void loadModels() {
        this.spatial=this.assetManager.loadModel(this.path);
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
       this.spatial.setName(this.name);
    }
    
    @Override
    public Spatial getSpatial(){
        return this.spatial;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
