package Controller;

import View.MainScene;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author Richard wijaya
 */
public class Main extends SimpleApplication implements ActionListener {

    private static long frame = 0;
    private long score = 0;
    private long highscore = 0;
    private BitmapText currentScore;
    private BitmapText highScore;
    private MainScene m;
    private BitmapText gameOverText;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        inputManager.addMapping("Restart", new KeyTrigger(keyInput.KEY_R));
        inputManager.addListener(this, "Restart");
        this.setDisplayFps(false);
        this.setDisplayStatView(false);
        m = new MainScene(this);
        stateManager.attach(m);
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));

        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText scoreText = new BitmapText(guiFont, false);
        scoreText.setSize(guiFont.getCharSet().getRenderedSize());
        scoreText.setText("Score: ");
        scoreText.setColor(ColorRGBA.Blue);
        scoreText.setLocalTranslation(100, scoreText.getLineHeight(), 0);
        guiNode.attachChild(scoreText);

        currentScore = new BitmapText(guiFont, false);
        currentScore.setSize(guiFont.getCharSet().getRenderedSize());
        currentScore.setText(score + "");
        currentScore.setColor(ColorRGBA.Blue);
        currentScore.setLocalTranslation(150, currentScore.getLineHeight(), 0);
        guiNode.attachChild(currentScore);

        BitmapText highScoreText = new BitmapText(guiFont, false);
        highScoreText.setSize(guiFont.getCharSet().getRenderedSize());
        highScoreText.setText("Highscore: ");
        highScoreText.setColor(ColorRGBA.Blue);
        highScoreText.setLocalTranslation(300, scoreText.getLineHeight(), 0);
        guiNode.attachChild(highScoreText);

        highScore = new BitmapText(guiFont, false);
        highScore.setSize(guiFont.getCharSet().getRenderedSize());
        highScore.setText("" + 0);
        highScore.setColor(ColorRGBA.Blue);
        highScore.setLocalTranslation(400, scoreText.getLineHeight(), 0);
        guiNode.attachChild(highScore);
        
        gameOverText = new BitmapText(guiFont, false);
        gameOverText.setSize(30f);
        gameOverText.setText("     GAME OVER \n Press R to Restart");
        gameOverText.setColor(ColorRGBA.Blue);
        gameOverText.setLocalTranslation(180, 250, 0);

    }

    @Override
    public void simpleUpdate(float tpf) {
        if (m.isEnabled()) {
            guiNode.detachChild(gameOverText);
            frame++;
            if (frame % 100 == 0) {
                score++;

                currentScore.setText("" + score);
            }
            if (frame == 1000000) {
                frame = 0;
            }
        }
        if(!m.isEnabled()){
            guiNode.attachChild(gameOverText);
            setHighScore();
        }
    }

    private void setHighScore() {
        if (Long.parseLong(currentScore.getText()) > Long.parseLong(highScore.getText())) {
            highscore = score;
            highScore.setText("" + highscore);

        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (!m.isEnabled() && name.equals("Restart")) {
            frame = 0;
            score = 0;
            currentScore.setText("0");
        }
    }

}
