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
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Ravi
 */
public class ImmobileObject extends AbstractControl {
    
    protected Material material;
    protected float x,y,z;//lokasi objek
    
    /**
     * Constructor untuk membuat sebuah objek diam
     * @param assetManager
     * @param name nama objek
     * @param path alamat objek
     * @param scale ukuran objek
     * @param x koordinat X objek
     * @param y koordinat Y objek
     * @param z koordinat Z objek
     */
    public ImmobileObject(AssetManager assetManager,String name, String path,
            float scale, float x, float y, float z) {
        this.spatial=assetManager.loadModel(path);
    
        this.spatial.setLocalScale(scale);
        this.spatial.setName(name);
    }
    
    public void setLocation(){
            this.spatial.setLocalTranslation(x, y, z);
    }
    /**
     * method untuk meng-update objek secar kontinu
     * @param tpf kecepatan perubahan objek
     */
    @Override
    protected void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
