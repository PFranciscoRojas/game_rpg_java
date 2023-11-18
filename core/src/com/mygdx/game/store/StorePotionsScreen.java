package com.mygdx.game.store;

import com.mygdx.game.classGame.inventory.Inventory;
import com.mygdx.game.classGame.inventory.Store;
import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.repository.CharacterRepository;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MainFirstScreen;
import com.mygdx.game.MyGdxGame;

public class StorePotionsScreen implements Screen {

    final MyGdxGame game;
    Skin skin;
    Skin skinTwo;
    Skin skinThree;
    Skin skinFour;
    Stage stage;
    Texture dropImage;
    Texture bucketImage;
    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    private int cash;
    Image selectedImage = null;
    Label selectedTittle = null;
    Label selectedParraf = null;
    Label selectGold = null;
    Label payGold = null;
    Label selectScore = null;
    Store store = Store.getInstance();
    Character character;
    Inventory inventory = Inventory.getInstance();
    CharacterRepository repository = new CharacterRepository();

    public StorePotionsScreen(MyGdxGame game) throws Exception {
        character = repository.getModel(1);

        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Crea un Skin para el estilo del texto
        skin = new Skin();
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFont.getData().setScale(1.0f); // Ajusta el tamaño de la fuente
        skin.add("default", new Label.LabelStyle(customFont, Color.WHITE));

        skinTwo = new Skin();
        BitmapFont customFontSecundary = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFontSecundary.getData().setScale(0.8f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado
        skinTwo.add("default", new Label.LabelStyle(customFontSecundary, Color.WHITE));

        skinThree = new Skin();
        BitmapFont customFontParraf = new BitmapFont(Gdx.files.internal("fonts/parrafText.fnt"));
        customFontParraf.getData().setScale(0.4f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado
        skinThree.add("default", new Label.LabelStyle(customFontParraf, Color.WHITE));

        skinFour = new Skin();
        BitmapFont customFontPrice = new BitmapFont(Gdx.files.internal("fonts/menuText.fnt"));
        customFontPrice.getData().setScale(1.0f); // Ajusta el valor (2.0f) según el tamaño de fuente deseado
        skinFour.add("default", new Label.LabelStyle(customFontPrice, Color.WHITE));

        //         Crea un fondo de imagen
        Image backgroundImage = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/RectangleF.png")))));
        backgroundImage.setBounds(200, Gdx.graphics.getHeight() - 550, 300, 400); // Ajusta el tamaño y la posición del fondo de imagen


        // Crea un cuadro de texto
        Label label = new Label("Lista De Posciones", skin);
        label.setPosition(120, Gdx.graphics.getHeight() - label.getHeight() - 85); // Ajusta la posición de la etiqueta de texto

        Label gold = new Label(String.valueOf("TU DINERO $" + character.getGold()), skin);
        gold.setPosition(650, Gdx.graphics.getHeight() - gold.getHeight() - 85); // Ajusta la posición de la etiqueta de texto
        gold.setVisible(true);

        String imagePathss = "img/Rectangle.png";



        // Crea el mosaico de imágenes


        // Agrega el fondo de imagen y el cuadro de texto al Stage

        stage.addActor(backgroundImage);
        stage.addActor(label);
        createImageGrid(imagePathss);
        CharacterGold();
        optionExit();
    }

    private void CharacterGold(){
        if (payGold != null) {
            // Oculta el Label de oro anterior
            payGold.setVisible(false);
        }

        // Muestra la imagen actual y actualiza la referencia
        Label gold = new Label(String.valueOf("TU DINERO $" + character.getGold()), skin);
        gold.setPosition(650, Gdx.graphics.getHeight() - gold.getHeight() - 85); // Ajusta la posición de la etiqueta de texto
        gold.setVisible(true);
        payGold = gold;
        stage.addActor(gold);
        // Acciones a realizar cuando se hace clic en el botón


    }

    private void optionExit(){
        Texture buttonTextureExit = new Texture(Gdx.files.internal("img/exit.png"));
        Skin skinFull = new Skin();
        skinFull.add("buttonImage", buttonTextureExit);
        ImageButton.ImageButtonStyle nuevoEstilof = new ImageButton.ImageButtonStyle();
        nuevoEstilof.imageUp = skinFull.getDrawable("buttonImage"); // Cambia "nuevaImagen" al nombre de tu nueva imagen
        ImageButton button = new ImageButton(nuevoEstilof);
        button.setSize(50, 50);
        button.setPosition(900, Gdx.graphics.getHeight() - 590);
        stage.addActor(button);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    game.setScreen(new MainFirstScreen(game));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void createImageGrid(String imagePathss) {
        float imageWidth = 100f; // Ajusta según sea necesario
        float imageHeight = 100f; // Ajusta según sea necesario
        float padding = 15f; // Espacio entre imágenes

        // Calcula la cantidad de filas necesarias
        int numRows = (int) Math.ceil(store.potions.size() / 3.0);

        // Posición inicial del mosaico
        float startX = 550f;
        float startY = Gdx.graphics.getHeight() - 350f; // Ajusta según sea necesario

// Crear las imágenes y agregarlas al Stage con funcionalidad de clic
        for (int i = 0; i < store.potions.size(); i++) {
            float x = startX + (i % 3) * (imageWidth + padding);
            float y = startY - (i / 3) * (imageHeight + padding);

            final int imageIndex = i; // Necesario para acceder al índice en el ClickListener

            Image images = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imagePathss)))));
            Image image = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(store.potions.get(imageIndex).getGraphicsElement())))));

            images.setBounds(x, y, imageWidth, imageHeight);
            image.setBounds(x, y, imageWidth, imageHeight);
            image.setTouchable(Touchable.enabled); // Hace que la imagen sea táctil

            Image select = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(store.potions.get(imageIndex).getGraphicsElement())))));
            select.setSize(200, 200);
            select.setVisible(false);
            select.setPosition(250, Gdx.graphics.getHeight() / 2);
            stage.addActor(select);

            Label titleElements = new Label(store.potions.get(imageIndex).getName(), skinTwo);
            titleElements.setVisible(false);
            titleElements.setPosition(210, Gdx.graphics.getHeight() - 400);
            stage.addActor(titleElements);

            Label parrafElements = new Label(store.potions.get(imageIndex).getDescription(), skinThree);
            parrafElements.setVisible(false);
            parrafElements.setPosition(210, Gdx.graphics.getHeight() - 420);
            stage.addActor(parrafElements);

            Label parrafScore = new Label( store.showScoreElementCatalog(store.potions,imageIndex) + String.valueOf(store.potions.get(imageIndex).getScore()), skinTwo);
            parrafScore.setVisible(false);
            parrafScore.setPosition(390, Gdx.graphics.getHeight() - 430);
            stage.addActor(parrafScore);


            Texture buttonTexture = new Texture(Gdx.files.internal("img/payAproved.png"));
            Texture buttonTextureExist = new Texture(Gdx.files.internal("img/payExist.png"));
            Texture buttonTextureFull = new Texture(Gdx.files.internal("img/payFull.png"));
            // Crear el estilo del botón

            Skin skinOrign = new Skin();
            skinOrign.add("buttonImage", buttonTexture);
            ImageButton.ImageButtonStyle originalStyle = new ImageButton.ImageButtonStyle();
            originalStyle.imageUp = skinOrign.getDrawable("buttonImage");

            Skin skinExist = new Skin();
            skinExist.add("buttonImage", buttonTextureExist);
            ImageButton.ImageButtonStyle nuevoEstilo = new ImageButton.ImageButtonStyle();
            nuevoEstilo.imageUp = skinExist.getDrawable("buttonImage"); // Cambia "nuevaImagen" al nombre de tu nueva imagen


            Skin skinFull = new Skin();
            skinFull.add("buttonImage", buttonTextureFull);
            ImageButton.ImageButtonStyle nuevoEstilof = new ImageButton.ImageButtonStyle();
            nuevoEstilof.imageUp = skinFull.getDrawable("buttonImage"); // Cambia "nuevaImagen" al nombre de tu nueva imagen




            // Crear el botón
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {


                    // Si ya hay una imagen seleccionada, la oculta
                    if (selectedImage != null) {
                        selectedImage.setVisible(false);
                        selectedTittle.setVisible(false);
                        selectedParraf.setVisible(false);
                        selectGold.setVisible(false);
                        selectScore.setVisible(false);

                    }

                    // Muestra la imagen actual y actualiza la referencia
                    select.setVisible(true);
                    selectedImage = select;
                    titleElements.setVisible(true);
                    selectedTittle = titleElements;
                    parrafElements.setVisible(true);
                    selectedParraf = parrafElements;
                    parrafScore.setVisible(true);
                    selectScore = parrafScore;

                    // Crea y muestra el botón
                    ImageButton button = new ImageButton(originalStyle);
                    button.setSize(250, 200);
                    button.setPosition(222, Gdx.graphics.getHeight() - 570);
                    stage.addActor(button);



                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            // Verifica si payGold no es null antes de intentar operar con él
                            try {
                                char response = store.buyProduct(imageIndex+1,inventory,character,store.potions);
                                switch (response) {
                                    case 'e':
                                        button.setStyle(nuevoEstilo);
                                        break;
                                    case 'c':
                                        repository.payElement(store.potions.get(imageIndex).getGold(),1);
                                        CharacterGold();
                                        break;
                                    case 'l':
                                        button.setStyle(nuevoEstilof);
                                        break;
                                    case 'f':
                                        button.setStyle(nuevoEstilof);
                                        break;
                                    case 'r':
                                        // secuencia de sentencias.
                                        break;
                                    default:
                                        System.out.println("null");
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }



                        }
                    });




                    // Crea y muestra la etiqueta del oro
                    Label goldElements = new Label(String.valueOf("$" + store.potions.get(imageIndex).getGold()), skinFour);
                    goldElements.setVisible(true);
                    goldElements.setPosition(325, Gdx.graphics.getHeight() - 485);
                    selectGold = goldElements;
                    stage.addActor(goldElements);
                }
            });

            stage.addActor(images);
            stage.addActor(image);


        }
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Limpia la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualiza y dibuja el Stage
        // Dibuja el fondo
        Texture backgroundTexture = new Texture("img/fondoDos.png");
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
