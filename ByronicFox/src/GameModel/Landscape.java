/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

/**
 *
 * @author Ravi
 */
public class Landscape extends ImmobileObject {
    
    protected Material material;
    
    public Landscape(AssetManager assetManager, String name, String path, 
            float scale, float x, float y, float z, Material material) {
        super(assetManager, name, path, scale, x, y, z);
        this.material=material;
    }
    
}
