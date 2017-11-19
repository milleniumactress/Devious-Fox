/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author Ravi
 */
public class Fox extends MainCharacter {

    /**
     * inisialisasi Fox
     *
     * @param assetManager
     * @param name nama fox
     * @param path alamat fox
     * @param scale ukuran fox
     * @param x koordinat X fox
     * @param y koordinat Y fox
     * @param z koordinat Z fox
     * @param material material fox
     */
    public Fox(AssetManager assetManager, String name, String path, float scale, float x, float y, float z, Material material) {
        super(assetManager, name, path, scale, x, y, z, material);
    }

    @Override
    public void setLocation(float x, float y, float z) {
        this.spatial.setLocalTranslation(x, y, z);
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

}