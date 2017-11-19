/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;

/**
 *
 * @author Ravi
 */
public abstract class MainCharacter extends GameObject {

    protected Material material;
    protected float x,y,z;//lokasi main character


    /**
     * inisialisasi Main Character
     *
     * @param assetManager
     * @param name nama main character
     * @param path alamat main character
     * @param scale ukuran main character
     * @param x koordinat X objek
     * @param y koordinat Y objek
     * @param z koordinat Z objek
     * @param material material main character
     */
    public MainCharacter(AssetManager assetManager, String name, String path,
            float scale, float x, float y, float z, Material material) {
        super(assetManager, name, path,scale);
        this.material = material;
        this.x=x;
        this.y=y;
        this.z=z;
        
    }
    
    public abstract void setLocation(float x, float y, float z);
    
    
    
}
