package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MissionOneScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
    Texture warriorImage;
    Texture eskeletonImage;
    Rectangle warrior;
    Rectangle eskeleton;

    public MissionOneScreen(final MyGdxGame game){
        this.game = game;
        warriorImage = new Texture(Gdx.files.internal("img/warrior.png"));
        eskeletonImage = new Texture(Gdx.files.internal("img/eskeleton.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        warrior = new Rectangle();
        warrior.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        warrior.y = 20; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        warrior.width = 164;
        warrior.height = 164;

        eskeleton = new Rectangle();
        eskeleton.x = 800 - 20 - 164; // center the bucket horizontally
        eskeleton.y = 20; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        eskeleton.width = 164;
        eskeleton.height = 164;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skin.add("default", new TextButton.TextButtonStyle(null, null, null, game.font));


        // Centro de la pantalla
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 4;

        // Configura el tamaño de fuente
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFont.getData().setScale(1.0f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibuja el fondo
        Texture backgroundTexture = new Texture("img/missionOneScreen.jpg");
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(warriorImage, warrior.x, warrior.y, warrior.width, warrior.height);
        game.batch.draw(eskeletonImage, eskeleton.x, eskeleton.y, eskeleton.width, eskeleton.height);

        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            warrior.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            warrior.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            warrior.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (warrior.x < 0)
            warrior.x = 0;
        if (warrior.x > 800 - 64)
            warrior.x = 800 - 64;

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        warriorImage.dispose();
    }
}
