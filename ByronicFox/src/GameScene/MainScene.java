/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScene;

import GameModel.*;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Ravi
 */
public class MainScene extends AbstractAppState implements ActionListener, AnimEventListener {

    private Node rootNode;
    Node landscapeNode = new Node("landscapeNode");
    Node obstacleNode = new Node("obstacleNode");
    Node enemyNode = new Node("enemyNode");
    Node mainCharacterNode = new Node("mainCharacterNode");
    private final Node localRootNode = new Node("placeHolder");
    private AssetManager assetManager;
    private BulletAppState bulletAppState;
    Random rnd = new Random();
    private float randomLocation = rnd.nextFloat() + 1;

    //atribut-atribut main character
    private MainCharacter mainCharacter;//ibi
    private CharacterControl mainCharacterControl;
    private Spatial mainCharacterSpatial;
    private InputManager inputManager;

    //atribut-atribut lantai
    private Landscape landscape;
    private Spatial ground;
    private LinkedList<Spatial> listOfGrounds;
    private LinkedList<Spatial> listOfObstacle;
    private Spatial landscapeSpatial;

    //atribut-atribut obstacle
    private Spatial obstacleSpatial;
    private Obstacle obstacle;
    private Enemy enemy;
    private Spatial enemySpatial;
    private LinkedList listOfenemy;
    private Material mainMaterial;

    //atribut-atribut kamera
    private FlyByCamera flyCamera;
    private Camera camera;
    private ChaseCamera chaseCamera;
    private Vector3f playerWalkDirection = Vector3f.ZERO;
    private CameraNode cameraNode;

    //action listener
    private ActionListener mainActionListener;
    //
    /**
     * constructor untuk membuat scene game. menempelkan semua objeck ke dalam
     * scene
     *
     * @param app
     */
    public MainScene(SimpleApplication app) {
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        flyCamera = app.getFlyByCamera();
        camera = app.getCamera();
        listOfGrounds = new LinkedList<>();
        listOfObstacle = new LinkedList<>();

        mainMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");

        mainCharacter = new MainCharacter(assetManager, "Fox", "Models/foxAnimations/foxAnimations.j3o",
                1f, 0, 0, 0, mainMaterial);//inisialisasi main character
        mainCharacterSpatial = mainCharacter.getSpatial();
        obstacle = new Obstacle(assetManager, "tree", "Models/Tree/Tree.j3o", 0.9f, 0, 0, 10, mainMaterial);//inisialisasi obstacle
        landscape = new Landscape(assetManager, "landscape", "Scenes/MainScene.j3o", 5f, 0, 0, 0, mainMaterial); //inisialisasi landscape
        enemy = new Enemy(assetManager, "falcon", "Models/falcone/falcone.j3o", 0.15f, 0, -randomLocation, 0, 0, 0, -30f, mainMaterial);//inisialisasi enemy

    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        rootNode.attachChild(localRootNode);

        bulletAppState.setDebugEnabled(false);
        
        //attach landscape, obstacle, enemy, 
        obstacleSpatial=obstacle.getSpatial();
        obstacleSpatial.setLocalTranslation(0, 0, 100);
        localRootNode.attachChild(landscape.getSpatial());
        localRootNode.attachChild(obstacleSpatial);
        localRootNode.attachChild(enemy.getSpatial());
        localRootNode.attachChild(mainCharacter.getSpatial());

        // mengambil object dari node untuk linked list
        ground = localRootNode.getChild("landscape");
        obstacleSpatial = localRootNode.getChild("tree");
        enemySpatial = localRootNode.getChild("falcon");
        enemySpatial.setLocalTranslation(0, 7f, 0);

        //update linkedlist
        listOfGrounds.addFirst(ground);
        listOfObstacle.addFirst(obstacleSpatial);
        listOfObstacle.addFirst(enemySpatial);

        //buat nempel
        RigidBodyControl ground_phy = new RigidBodyControl(0.0f);
        ground.addControl(ground_phy);
        obstacleSpatial.setMaterial(mainMaterial);
        bulletAppState.getPhysicsSpace().add(ground_phy);
        bulletAppState.getPhysicsSpace().add(obstacleSpatial);
        bulletAppState.getPhysicsSpace().add(enemySpatial);

        //set main character bounding
        BoundingBox bb = (BoundingBox) mainCharacterSpatial.getWorldBound();
        float radius = bb.getXExtent();
        float height = bb.getYExtent();
        CapsuleCollisionShape mainCharacterShape = new CapsuleCollisionShape(radius, height);

        //main character control
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Jump");
        inputManager.addMapping("Restart", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addListener(this, "Restart");
        //inputManager.
        mainCharacterControl = new CharacterControl(mainCharacterShape, 1.0f);
        mainCharacterControl.setJumpSpeed(40);
        mainCharacterControl.setGravity(100);
        mainCharacterSpatial.addControl(mainCharacterControl);
        bulletAppState.getPhysicsSpace().add(mainCharacterControl);

        //camera work
        flyCamera.setMoveSpeed(100f);
        chaseCamera = new ChaseCamera(camera, mainCharacterSpatial, inputManager);
        chaseCamera.setDefaultHorizontalRotation(-3.1f);
        chaseCamera.setDefaultVerticalRotation(0f);
        setUpLight();
        rootNode.attachChild(localRootNode);

    }

    private void setUpLight() {
        // We add light so we see the scene
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }

    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);
        super.cleanup();

    }

    @Override
    public void update(float tpf) {

//untuk object
  
        Vector3f v2 = mainCharacterSpatial.getLocalTranslation();
        Iterator<Spatial> orbit = listOfObstacle.iterator();
        while (orbit.hasNext()) {
            Spatial newSpatial = orbit.next();
            Vector3f v1 = newSpatial.getLocalTranslation();
            float x = Math.abs(v1.x - v2.x);
            float y = Math.abs(v1.y - v2.y);
            float z = Math.abs(v1.z - v2.z);
            if (x <= 0.5 && y <= 1.6 && z <= 0.5) {
                 setEnabled(false);

            }
            newSpatial.move(0, 0, tpf * -20);
            if (newSpatial.getLocalTranslation().getZ() <= -20) {
                newSpatial.getLocalTranslation().setZ(50);
            }

        }
//untuk terrain
        Iterator<Spatial> it = listOfGrounds.iterator();
        while (it.hasNext()) {
            Spatial n = it.next();
            n.move(0, 0, -20 * tpf);
            if (n.getLocalTranslation().getZ() <= -300) {
                n.getLocalTranslation().setZ(n.getLocalTranslation().getZ() + 200);
            }
            

        }

        enemySpatial.move(new Vector3f(0, 0, -20 * tpf));


    }
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Jump") && !isPressed&&isEnabled()) {
            mainCharacterControl.jump();
        }
        if(name.equals("Restart")&& !isPressed && !isEnabled()){
            setEnabled(true);
            inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
            obstacleSpatial.setLocalTranslation(0, 0, 50);
            enemySpatial.setLocalTranslation(0, 7f, 25);
        }
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

