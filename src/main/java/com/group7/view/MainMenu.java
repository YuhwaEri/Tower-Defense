package com.group7.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends VBox{
    // internal objects
    private static final Stop[] btnLGStops =
            { new Stop(1, Color.BEIGE), new Stop(0, Color.BISQUE)};
    private static final LinearGradient btnLG = new LinearGradient(0, 1, 0, 0,
            true, CycleMethod.NO_CYCLE, btnLGStops);
    private final VBox optionsMenu = new VBox();
    private final Font menuFont = new Font("Source Code Pro", 24);
    private final TowerDefenseView view;

    /**
     * constructor that sets the view and calls setup methods
     * @param view which is the TowerDefenseView objects
     */
    public MainMenu(TowerDefenseView view) {
        this.view = view;
        setupMainMenu();
        setupOptions();
    }

    private void setupMainMenu(){
        setAlignment(Pos.CENTER);
        setSpacing(20);
        //original image at https://wall.alphacoders.com/big.php?i=976895
        setBackground(new Background(new BackgroundImage(
                TowerDefenseView.mainMenuBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        // setting the buttons
        Button play = new Button("PLAY");
        Button options = new Button("OPTIONS");
        Button quit = new Button("QUIT");
        List<Button> buttons = Arrays.asList(play, options, quit);
        buttons.forEach(this::applyButtonStyles);
        // setting the buttons to interact with the main view
        play.setOnAction(event -> view.transitionBetweenGames());
        quit.setOnAction(actionEvent -> System.exit(0));
        options.setOnAction(event -> view.updateWindow(optionsMenu));
        getChildren().addAll(buttons);
    }

    private void setupOptions() {
        optionsMenu.setBackground(
                new Background(new BackgroundImage(
                TowerDefenseView.mainMenuBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        optionsMenu.setAlignment(Pos.CENTER);
        optionsMenu.setSpacing(15);

        // setting up the game speed settings options
        HBox sliderRow = new HBox();
        sliderRow.setAlignment(Pos.CENTER);
        sliderRow.setSpacing(15);
        Slider gameSpeedSlider = new Slider(1, 5, 1);
        gameSpeedSlider.setPrefWidth(200);
        gameSpeedSlider.setShowTickLabels(false);
        gameSpeedSlider.setBlockIncrement(1);
        Text sliderMin = new Text("1");
        Text sliderMax = new Text("5");
        Text sliderVal = new Text("Game speed: 1x");
        Arrays.asList(sliderMin, sliderMax, sliderVal).forEach(label -> {
            label.setFont(new Font("Source Code Pro", 28));
            label.setFill(Color.WHITE);
        });
        sliderRow.getChildren().addAll(sliderMin, gameSpeedSlider, sliderMax);
        gameSpeedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //view.updateGameSpeed(newValue.doubleValue());
            sliderVal.setText("Game speed: " + newValue.intValue() + "x");
        });

        // setting up the volume settings
        HBox volumeSliderRow = new HBox();
        volumeSliderRow.setAlignment(Pos.CENTER);
        volumeSliderRow.setSpacing(15);
        Slider volumeSlider = new Slider(0, 100, 30);
        volumeSlider.setPrefWidth(200);
        volumeSlider.setShowTickLabels(false);
        volumeSlider.setBlockIncrement(10);
        Text volumeMin = new Text("  0");
        Text volumeMax = new Text("10");
        Text volumeVal = new Text("Volume: 30%");
        Arrays.asList(volumeMin, volumeMax, volumeVal).forEach(label -> {
        	label.setFont(new Font("Source Code Pro", 28));
        	label.setFill(Color.WHITE);
        });
        volumeSliderRow.getChildren().addAll(volumeMin, volumeSlider, volumeMax);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        volumeVal.setText("Volume: " + newValue.intValue() + "%");
        view.updateVolume(newValue.doubleValue()/100);
    	});
        // setting up the back button
        Button back = new Button("Back");
        applyButtonStyles(back);
        back.setOnAction(event -> view.updateWindow(this));
        optionsMenu.getChildren().addAll(sliderVal, sliderRow, volumeVal, volumeSliderRow, back);
    }

    /**
     * This method will apply a style to each of the buttons created above
     * @param btn is an internal button that lacks formatting
     */
    private void applyButtonStyles(Button btn) {
        btn.setFont(menuFont);
        btn.setPadding(new Insets(6, 100, 6, 100));
        btn.setPrefWidth(Math.max(400, TowerDefenseView.WINDOW_WIDTH / 2));
        Background hov = new Background(new BackgroundFill(
                Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY));
        Background idle = new Background(new BackgroundFill(btnLG,
                CornerRadii.EMPTY, Insets.EMPTY));
        btn.setBackground(idle);
        btn.setOnMouseEntered(event -> btn.setBackground(hov));
        btn.setOnMouseExited(event -> btn.setBackground(idle));
    }
}
