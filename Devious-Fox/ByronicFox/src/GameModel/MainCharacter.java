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
public class MainCharacter extends GameObject {

    protected Material material;
    protected ActionListener actionListener;
    protected CharacterControl mainCharacterContorl;

    /**
     * inisialisasi Main Character
     *
     * @param assetManager
     * @param name
     * @param path
     * @param scale
     * @param x
     * @param y
     * @param z
     * @param material
     */
    public MainCharacter(AssetManager assetManager, String name, String path,
            float scale, float x, float y, float z, Material material) {
        super(assetManager, name, path, scale, x, y, z);
        this.material = material;
    }
    
    /**
     * method untuk mengikat tombol dengan karakter
     * @param inputManager 
     */
    public void setUpMapping(InputManager inputManager){
        inputManager.addMapping("jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, "jump");
    }

    @Override
    public void setLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method untuk aksi-aksi yang dilakukan oleh karakter
     * @param name
     * @param isPressed
     * @param tpf 
     */
//    @Override
//    public void onAction(String name, boolean isPressed, float tpf) {
//        if (name.equals("Jump") && !isPressed) {
//            mainCharacterContorl.jump();
//        }
//
//    }
}
