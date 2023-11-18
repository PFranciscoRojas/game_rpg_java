package com.mygdx.game;

import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Mission;
import com.mygdx.game.classGame.repository.CharacterRepository;
import com.mygdx.game.classGame.repository.MissionRepository;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MissionOneScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
    Skin skinTwo;
    Texture warriorImage;
    Texture eskeletonImage;
    Rectangle warrior;
    Rectangle eskeleton;

    Character character = new Character();

    Mission mission= new Mission();
    CharacterRepository repository = new CharacterRepository();
    MissionRepository repositoryMission=new MissionRepository();

    public MissionOneScreen(final MyGdxGame game){

        try {
            character = repository.getModel(1);
            mission=repositoryMission.getModel(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFont.getData().setScale(0.9f); // Ajusta el tamaño de la fuente
        skin.add("default", new Label.LabelStyle(customFont, Color.BLACK));

        // Centro de la pantalla
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 4;

        Label.LabelStyle labelStyle = new Label.LabelStyle(customFont, Color.BLACK);

        Label tituloMision = new Label("Titulo " + mission.getName(), skin);
        tituloMision.setPosition(centerX - tituloMision.getWidth() / 2, Gdx.graphics.getHeight() - 50); // Ajusta la posición vertical según sea necesario

        Label descripcionMision = new Label("Descripcion " + mission.getDescription(), labelStyle);
        descripcionMision.setWrap(true); // Habilita el ajuste de texto para múltiples líneas

        // Establece el ancho máximo que deseas mostrar
        float maxWidth = 800f;
        descripcionMision.setWidth(maxWidth);

        // Ajusta la posición vertical para que la descripción aparezca debajo del título
        descripcionMision.setPosition(centerX - descripcionMision.getWidth() / 2, tituloMision.getY() - 55);
        // Agregar el Label al Stage
        stage.addActor(tituloMision);
        stage.addActor(descripcionMision);



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
