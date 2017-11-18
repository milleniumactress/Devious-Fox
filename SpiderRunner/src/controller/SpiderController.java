/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Spatial;
import models.Spider;

/**
 *
 * @author Richard
 */
public class SpiderController implements AnimEventListener, ActionListener {

    private CharacterControl spiderControl;
    private InputManager inputManager;
    private Spider spider;

    public SpiderController(CharacterControl spiderControl, InputManager inputManager, Spider spider) {
        this.spiderControl = spiderControl;
        this.spider=spider;
        this.inputManager = inputManager;

    }

    public void setKeys(CapsuleCollisionShape spiderShape) {
        this.inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        this.inputManager.addListener(this, "Jump");
        this.inputManager.addMapping("Restart", new KeyTrigger(KeyInput.KEY_R));
        this.inputManager.addListener(this, "Restart");

        this.spiderControl = new CharacterControl(spiderShape, 1.0f);
        spiderControl.setJumpSpeed(40);
        spiderControl.setGravity(100);
        spider.getSpatial().addControl(spiderControl);
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Jump") && !isPressed) {
            spiderControl.jump();
        }
    }

}
