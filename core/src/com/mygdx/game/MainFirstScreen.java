package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.inventory.EquipmentScreen;
import com.mygdx.game.inventory.InventoryScreen;
import com.mygdx.game.store.MenuStore;


public class MainFirstScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
    Sound dropSound;
    public <y> MainFirstScreen(final MyGdxGame game) throws Exception {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skin.add("default", new TextButton.TextButtonStyle(null, null, null, game.font));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("soundTrack/soundtrackMenu.mp3"));

        // Centro de la pantalla
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 4;




// Configura el tamaño de fuente
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFont.getData().setScale(1.0f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado


        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        final TextButton button1 = new TextButton("Misiones", skin, "default");
        buttonStyle.font = customFont;
        button1.setStyle(buttonStyle); // Aplica el estilo al botón
        button1.setPosition(centerX - button1.getWidth() / 2, centerY + 50);
        buttonStyle.fontColor = Color.GRAY;
        button1.addListener(new ClickListener() {

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    buttonStyle.fontColor = Color.WHITE;
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    buttonStyle.fontColor = Color.GRAY;
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //game.setScreen(new GameScreen(game));
                    try {
                        game.setScreen(new MenuMission(game));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        final TextButton.TextButtonStyle buttonStyle2 = new TextButton.TextButtonStyle();
        buttonStyle2.font = customFont;
        TextButton button2 = new TextButton("Tienda",  skin, "default");
        button2.setStyle(buttonStyle2);
        button2.setPosition(centerX - button2.getWidth() / 2, centerY);
        buttonStyle2.fontColor = Color.GRAY;
        button2.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttonStyle2.fontColor = Color.WHITE;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttonStyle2.fontColor = Color.GRAY;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Acciones al hacer clic en el botón
                try {
                    game.setScreen(new MenuStore(game));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        final TextButton.TextButtonStyle buttonStyle3 = new TextButton.TextButtonStyle();
        buttonStyle3.font = customFont;
        TextButton button3 = new TextButton("Inventario", skin);
        button3.setStyle(buttonStyle3);
        button3.setPosition(centerX - button3.getWidth() / 2, centerY - 50);
        buttonStyle3.fontColor = Color.GRAY;
        button3.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    buttonStyle3.fontColor = Color.WHITE;
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    buttonStyle3.fontColor = Color.GRAY;

                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Acciones al hacer clic en el botón
                    try {
                        game.setScreen(new InventoryScreen(game));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        });

        final TextButton.TextButtonStyle buttonStyle4 = new TextButton.TextButtonStyle();
        buttonStyle4.font = customFont;
        TextButton button4 = new TextButton("Equipamento", skin);
        button4.setStyle(buttonStyle4);
        button4.setPosition(centerX - button4.getWidth() / 2, centerY - 100);
        buttonStyle4.fontColor = Color.GRAY;
        button4.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttonStyle4.fontColor = Color.WHITE;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttonStyle4.fontColor = Color.GRAY;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Acciones al hacer clic en el botón
                try {
                    game.setScreen(new EquipmentScreen(game));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        dropSound.play();
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibuja el fondo
        Texture backgroundTexture = new Texture("img/fondo.jpg");
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

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

    }
    // Resto de implementaciones de métodos de Screen y otras acciones aquí
}
