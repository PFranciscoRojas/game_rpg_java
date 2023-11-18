package com.mygdx.game;

import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.repository.CharacterRepository;
import com.mygdx.game.classGame.repository.MissionRepository;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuMission implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
    Character character = new Character();
    CharacterRepository repository = new CharacterRepository();
    MissionRepository repositoryMission=new MissionRepository();
    Integer [] opciones;

    public <y> MenuMission(final MyGdxGame game) throws Exception{
        opciones = new Integer[2];
        character = repository.getModel(1);
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skin.add("default", new Label.LabelStyle(game.font, Color.WHITE));
        skin.add("default", new TextButton.TextButtonStyle(null, null, null, game.font));
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = game.font;  // Ajusta el tipo de fuente según tus necesidades
        // Ajusta según tus necesidades
        skin.add("default", windowStyle, Window.WindowStyle.class);
        // Centro de la pantalla
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 4;

        // Configura el tamaño de fuente
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFont.getData().setScale(1.0f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado

        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        final TextButton button1 = new TextButton("Mision 1", skin, "default");
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
                Dialog dialog = new Dialog("MISION 1", skin) {
                    @Override
                    protected void result(Object object) {
                        // Este método se llama cuando el cuadro de diálogo se cierra
                        if (object != null && object.equals("si")) {
                            // Acción si se selecciona "si"
                            try {
                                opciones[0]=character.getId();
                                opciones[1]=1;
                                repositoryMission.saveModel(opciones);
                                game.setScreen(new MissionOneScreen(game));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };

                // Agrega un mensaje al cuadro de diálogo
                Label label = new Label("¿confirma ir a la Mision 1?", skin); // "white" es el nombre del estilo de la fuente blanca
                customFont.getData().setScale(1.0f);
                dialog.getContentTable().add(label).pad(20).center();

                // Agrega botones al cuadro de diálogo
                TextButton siButton = new TextButton("Sí", skin);
                TextButton noButton = new TextButton("No", skin);

                // Define acciones para los botones
                siButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                    }
                });
                noButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                    }
                });

                // Agrega los botones al cuadro de diálogo
                dialog.button(siButton, "si");
                dialog.button(noButton, "no");
                Texture backgroundTexture = new Texture(Gdx.files.internal("img/black_background.jpg"));
                dialog.setBackground(new Image(backgroundTexture).getDrawable());
                // Muestra el cuadro de diálogo
                dialog.show(stage);
            }
        });

        final TextButton.TextButtonStyle buttonStyle2 = new TextButton.TextButtonStyle();
        final TextButton button2 = new TextButton("Regresar al menu", skin, "default");
        buttonStyle2.font = customFont;
        button2.setStyle(buttonStyle2); // Aplica el estilo al botón
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

                try {
                    game.setScreen(new MainFirstScreen(game));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        stage.addActor(button1);
        stage.addActor(button2);
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
}
