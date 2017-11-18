/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Ravi
 */
public abstract class GameObject  {
    
    protected String name;//nama objek
    protected String path;//alamat objek
    protected Material material;//material objek
    protected AssetManager assetManager;
    protected float posX,posY,posZ;//posisi objek
    protected Spatial spatial;
    
    /**
     * 
     * @param name
     * @param path
     * @param assetManager
     * @param x
     * @param y
     * @param z 
     */
    public GameObject(String name, String path, AssetManager assetManager, float x, float y, float z){
        this.name=name;
        this.path=path;
        this.assetManager=assetManager;
        this.posX=x;
        this.posY=y;
        this.posZ=z;
    }
    
    /**
     * 
     * @return 
     */
    public abstract void loadModels();
    /**
     * 
     * @param material 
     */
    public abstract void setMaterials(Material material);
    /**
     * 
     * @param x
     * @param y
     * @param z 
     */
    public abstract void setTransLation(float x,float y, float z);
    
    public abstract void setName(String name);
    
    public abstract String getName();
    
    public abstract Spatial getSpatial();
    
}
