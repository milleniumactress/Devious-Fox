/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import controller.ObstacleController;
import java.util.Random;
import controller.*;
import models.*;

/**
 *
 * @author Ravi
 */
public class MainScene extends AbstractAppState {

    private Node rootNode;
    Node obstaclesNode = new Node("obstacleNode");
    Node mainCharacterNode = new Node("mainCharacterNode");
    private final Node localRootNode = new Node("placeHolder");
    private AssetManager assetManager;
    private BulletAppState bulletAppState;
    Random rnd = new Random();
    private float randomLocation = rnd.nextFloat() + 1;

    private Material mainMaterial;

    private Spider spider;
    private Obstacle[] obstaclesList;
    private InputManager inputManager;

    private Landscape landscape;

    private FlyByCamera flyCamera;
    private Camera camera;
    private ChaseCamera chaseCamera;
    private Vector3f playerWalkDirection = Vector3f.ZERO;
    private CameraNode cameraNode;

    private ObstacleController obstacleController;

    public MainScene(SimpleApplication app) {

        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        flyCamera = app.getFlyByCamera();
        camera = app.getCamera();
        mainMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");

        spider = new Spider("Spider", "Models/spider/spider.j3o", assetManager,
                0, 0, 0);
        obstaclesList = new Obstacle[20];
        Random r = new Random();
        for (int i = 0; i < obstaclesList.length; i++) {
            boolean type = r.nextBoolean();
            if (type) {
                obstaclesList[i] = new Tree("Tree", "Models/Tree/tree.j3o", assetManager,
                        0, 0, 10);
            } else {
                obstaclesList[i] = new Bird("Bird", "Models/bird/bird.j3o", assetManager,
                        0, 0.15f, 0);
            }
        }
        landscape = new Landscape("Terrain", "Models/Cherry Tree/Cherry Tree.j3o", assetManager,
                0, 0, 0);
        obstacleController = new ObstacleController(obstaclesList);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        rootNode.attachChild(localRootNode);

        bulletAppState.setDebugEnabled(false);

        localRootNode.attachChild(landscape.getSpatial());
        localRootNode.attachChild(spider.getSpatial());

        RigidBodyControl ground_phy = new RigidBodyControl(0.0f);
        landscape.getSpatial().addControl(ground_phy);
        bulletAppState.getPhysicsSpace().add(ground_phy);

        for (Obstacle obstaclesList1 : obstaclesList) {
            localRootNode.attachChild(obstaclesList1.getSpatial());
            bulletAppState.getPhysicsSpace().add(obstaclesList1.getSpatial());
        }

        BoundingBox bb = (BoundingBox) spider.getSpatial().getWorldBound();
        float radius = bb.getXExtent();
        float height = bb.getYExtent();
        CapsuleCollisionShape mainCharacterShape = new CapsuleCollisionShape(radius, height);

        chaseCamera = new ChaseCamera(camera, spider.getSpatial(), inputManager);
        chaseCamera.setDefaultHorizontalRotation(-3.1f);
        chaseCamera.setDefaultVerticalRotation(0.2f);
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
        Vector3f v2 = spider.getSpatial().getLocalTranslation();
        Random rand = new Random();
//untuk object
        while (true) {
            obstacleController.updateGround(tpf);
//            if (x <= 0.5 && y <= 1.6 && z <= 0.5) {
            ///           setEnabled(false);
            ///        inputManager.clearRawInputListeners();
            //System.out.println("game over");

        }

    }
//untuk terrain
//        Iterator<Spatial> it = listOfGrounds.iterator();
//        while (it.hasNext()) {
//            Spatial n = it.next();
//            n.move(0, 0, -20 * tpf);
//            if (n.getLocalTranslation().getZ() <= -300) {
//                n.getLocalTranslation().setZ(n.getLocalTranslation().getZ() + 200);
//           }

}

//        enemySpatial.move(new Vector3f(0, 0, -20 * tpf));}
