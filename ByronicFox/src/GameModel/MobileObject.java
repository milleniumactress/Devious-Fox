/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.asset.AssetManager;

/**
 *
 * @author Ravi
 */
public class MobileObject extends ImmobileObject {
    
    protected float vx,vy,vz;
    
    public MobileObject(AssetManager assetManager, String name, String path, 
            float scale, float x, float y, float z,
            float vx,float vy, float vz) {
        super(assetManager, name, path, scale, x, y, z);
        this.vx=vx;
        this.vy=vy;
        this.vz=vz;
        this.spatial.setLocalTranslation(x, y, z);
    }

    @Override
    protected void controlUpdate(float tpf) {
        this.spatial.setLocalTranslation(vx*tpf, vy*tpf, vz*tpf);
    }
    
    
    
}
