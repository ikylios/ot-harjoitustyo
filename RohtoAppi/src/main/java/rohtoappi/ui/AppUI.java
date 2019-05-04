package rohtoappi.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rohtoappi.domain.AppLogic;

/**
 *
 * @author xilxilx
 */
public class AppUI extends Application {

    AppLogic logic;
    Insets padding;
    UIBuilder uiBuilder;
    Background background;
    String buttonStyle;

    public void giveLogic(AppLogic logic) {
        this.logic = logic;
        padding = new Insets(20, 20, 20, 20);
        background = new Background(new BackgroundFill(Color.rgb(71, 35, 97), CornerRadii.EMPTY, Insets.EMPTY));
        buttonStyle = "-fx-background-color: #8872a5; -fx-text-fill: #ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;";
        uiBuilder = new UIBuilder();
    }

    @Override
    public void start(Stage window) {

        Button createButton = new Button("Create A Potion");
        createButton.setMaxWidth(200.0);
        createButton.setStyle(buttonStyle);
        Button potionLibButton = new Button("Potion Library");
        potionLibButton.setMaxWidth(200.0);
        potionLibButton.setStyle(buttonStyle);
        Button ingredientLibButton = new Button("Ingredient Library");
        ingredientLibButton.setMaxWidth(200.0);
        ingredientLibButton.setStyle(buttonStyle);
        Button exitButton = new Button("Exit");
        exitButton.setStyle(buttonStyle);

        VBox mainButtons = new VBox();
        mainButtons.setAlignment(Pos.CENTER);
        mainButtons.setPrefWidth(150.0);
        mainButtons.setSpacing(20);
        mainButtons.getChildren().addAll(createButton, potionLibButton, ingredientLibButton, exitButton);
        mainButtons.setBackground(background);

        Scene main = new Scene(mainButtons);

        exitButton.setOnAction((event) -> {
            stop();
        });

        createButton.setOnAction((event) -> {
            createAPotion(window);
        });

        potionLibButton.setOnAction((event) -> {
            potionLibrary(window);
        });

        ingredientLibButton.setOnAction((event) -> {
            ingredientLibrary(window);
        });

        window.setScene(main);
        window.show();

    }

    public void stop() {
        logic.ingredientLibrary.writeToFile();
        logic.potionLibrary.writeToFile();
        Platform.exit();
    }

    public void createAPotion(Stage window) {
        BorderPane createComponents = new BorderPane();

        Label titleLabel = uiBuilder.createSceneTitle("Create A Potion");
        createComponents.setTop(titleLabel);

        ListView<String> listView = new ListView();
        ObservableList<String> ingredientList = FXCollections.observableArrayList(logic.tempPotion.getIngredientsString());
        if (!ingredientList.isEmpty()) {
            listView.setItems(ingredientList);
            listView.setMaxWidth(350.0);
            listView.setMaxHeight(300.00);
            listView.setFixedCellSize(30.0);
            createComponents.setCenter(listView);
        } else {
            Label ingredientEmpty = new Label("No ingredients. Empty.");
            ingredientEmpty.setTextFill(Color.WHITE);
            ingredientEmpty.setAlignment(Pos.TOP_CENTER);
            createComponents.setCenter(ingredientEmpty);
        }

        HBox createButtons = new HBox();
        createButtons.setSpacing(10);
        Button confirmPotion = new Button("Confirm Potion");
        confirmPotion.setStyle(buttonStyle);
        Button backToMenu = new Button("Back To Main Menu");
        backToMenu.setStyle(buttonStyle);
        Button randomisePotion = new Button("Randomize");
        randomisePotion.setStyle(buttonStyle);
        Button clearPotion = new Button("Clear Potion");
        clearPotion.setStyle(buttonStyle);
        createButtons.getChildren().addAll(confirmPotion, backToMenu, randomisePotion, clearPotion);
        createButtons.setAlignment(Pos.CENTER);
        if (logic.tempPotion.getIngredients().isEmpty()) {
            confirmPotion.setDisable(true);
        }

        VBox ingredientButtons = new VBox();
        ingredientButtons.setSpacing(20);
        Button addIngredient = new Button("Add Ingredient");
        addIngredient.setMaxWidth(300);
        addIngredient.setStyle(buttonStyle);
        Button removeIngredient = new Button("Remove Ingredient");
        removeIngredient.setMaxWidth(300);
        removeIngredient.setStyle(buttonStyle);
        Button editIngredient = new Button("Edit Amount");
        editIngredient.setStyle(buttonStyle);
        editIngredient.setMaxWidth(300);
        ingredientButtons.getChildren().addAll(addIngredient, removeIngredient, editIngredient);

        createComponents.setBottom(createButtons);
        createComponents.setRight(ingredientButtons);
        createComponents.setPadding(padding);
        createComponents.setBackground(background);

        backToMenu.setOnAction((event) -> {
            start(window);
        });

        confirmPotion.setOnAction((event) -> {
            logic.tempPotion.generateMagic();
            generatedPotionScene(window);
        });

        randomisePotion.setOnAction((event) -> {
            logic.randomisePotion();
            createAPotion(window);
        });

        addIngredient.setOnAction((event) -> {
            addToPotionList(window);
        });

        removeIngredient.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String[] selectedPieces = selected.split("\t\t");
                logic.tempPotion.removeFromPotion(selectedPieces[0]);
                createAPotion(window);
            }
        });

        editIngredient.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String[] selectedPieces = selected.split("\t\t");
                editIngredient(window, selectedPieces[0]);
            }
        });

        clearPotion.setOnAction((event) -> {
            logic.clearTempPotion();
            createAPotion(window);
        });

        Scene createAPotion = new Scene(createComponents);
        window.setScene(createAPotion);

        window.show();
    }

    public void addToPotionList(Stage window) {
        BorderPane ingredientComponents = new BorderPane();

        Label titleLabel = uiBuilder.createSceneTitle("Ingredients");
        ingredientComponents.setTop(titleLabel);

        ObservableList<String> ingredientList = FXCollections.observableArrayList(logic.ingredientLibrary.getIngredientsNames());
        ListView<String> listView = new ListView();
        listView.setItems(ingredientList);
        listView.setMaxWidth(450.0);

        HBox ingredientButtons = new HBox();
        ingredientButtons.setSpacing(20);
        Button backToPotion = new Button("Back");
        backToPotion.setStyle(buttonStyle);
        Button addIngredient = new Button("Add to Potion");
        addIngredient.setStyle(buttonStyle);
        ingredientButtons.getChildren().addAll(addIngredient, backToPotion);
        ingredientButtons.setAlignment(Pos.CENTER);
        ingredientButtons.setPadding(padding);

        backToPotion.setOnAction((event) -> {
            createAPotion(window);
        });

        addIngredient.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                addIngredientToPotion(window, selected);
            }
        });

        ingredientComponents.setCenter(listView);
        ingredientComponents.setBottom(ingredientButtons);
        ingredientComponents.setBackground(background);

        ingredientComponents.setPadding(padding);

        Scene ingredientLibrary = new Scene(ingredientComponents);
        window.setScene(ingredientLibrary);

        window.show();
    }

    public void addIngredientToPotion(Stage window, String name) {
        BorderPane components = new BorderPane();

        Label addingLabel = new Label("Adding ingredient ");
        addingLabel.setTextFill(Color.WHITE);
        Label nameLabel = new Label(name + ".");
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        HBox nameFields = new HBox();
        nameFields.getChildren().addAll(addingLabel, nameLabel);
        nameFields.setAlignment(Pos.CENTER);

        Label amountLabel = new Label("Amount: ");
        amountLabel.setTextFill(Color.WHITE);
        TextField amountField = new TextField();
        amountField.setStyle(buttonStyle);
        String unit = logic.ingredientLibrary.getIngredientByName(name).getMeasuringUnit();
        Label unitLabel = new Label(" " + unit);
        unitLabel.setTextFill(Color.WHITE);

        HBox amountFields = new HBox();
        amountFields.getChildren().addAll(amountLabel, amountField, unitLabel);
        amountFields.setSpacing(5);
        amountFields.setAlignment(Pos.CENTER);

        VBox fields = new VBox();
        fields.getChildren().addAll(nameFields, amountFields);
        fields.setSpacing(20);
        fields.setAlignment(Pos.CENTER);

        components.setCenter(fields);

        HBox buttons = new HBox();
        Button confirm = new Button("Confirm");
        confirm.setStyle(buttonStyle);
        Button cancel = new Button("Cancel");
        cancel.setStyle(buttonStyle);
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        status.setTextFill(Color.WHITE);
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);
        buttonsAndStatus.getChildren().addAll(status, buttons);

        components.setBottom(buttonsAndStatus);
        components.setPadding(padding);
        components.setBackground(background);

        cancel.setOnAction((event) -> {
            addToPotionList(window);
        });

        confirm.setOnAction((event) -> {
            if (!amountField.getText().isEmpty()) {
                String retVal = logic.addToTempPotion(name, amountField.getText());
                String response = "";
                if (retVal.equals("ingredientPresent")) {
                    response = "Ingredient is already in potion.";
                } else if (retVal.equals("invalidValue")) {
                    response = "Not a valid value.";
                } else if (retVal.equals("limit")) {
                    response = "Please limit the amount to 9 digits or less.";
                } else {
                    amountField.clear();
                    addToPotionList(window);
                }
                status.setText(response);
            }
        });

        Scene addIngredientScene = new Scene(components);
        window.setScene(addIngredientScene);

        window.show();
    }

    public void editIngredient(Stage window, String name) {
        BorderPane components = new BorderPane();
        components.setBackground(background);
        components.setPadding(padding);

        HBox fields = new HBox();
        TextField amountField = new TextField();
        amountField.setStyle(buttonStyle);
        Label amountLabel = new Label("New amount:");
        amountLabel.setTextFill(Color.WHITE);
        fields.getChildren().addAll(amountLabel, amountField);
        fields.setSpacing(5);
        fields.setAlignment(Pos.CENTER);

        VBox infoAndField = new VBox();
        Label currentAmount = new Label("Ingredient " + name + " is currently "
                + logic.tempPotion.getIngredientByName(name).getAmount() + " " + logic.tempPotion.getIngredientByName(name).getMeasuringUnit() + ".");
        currentAmount.setTextFill(Color.WHITE);
        infoAndField.setSpacing(20);
        infoAndField.getChildren().addAll(currentAmount, fields);
        infoAndField.setAlignment(Pos.CENTER);

        components.setCenter(infoAndField);

        HBox buttons = new HBox();
        Button confirm = new Button("Confirm");
        confirm.setStyle(buttonStyle);
        Button cancel = new Button("Cancel");
        cancel.setStyle(buttonStyle);
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        status.setTextFill(Color.WHITE);
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);
        buttonsAndStatus.getChildren().addAll(status, buttons);

        components.setBottom(buttonsAndStatus);

        cancel.setOnAction((event) -> {
            createAPotion(window);
        });

        confirm.setOnAction((event) -> {
            String newAmount = amountField.getText();
            String response = "Unknown Error.";
            String retVal = logic.tempPotion.editAmount(name, newAmount);
            if (retVal.equals("clear")) {
                amountField.clear();
                createAPotion(window);
            } else {
                response = "Not a valid value.";
            }

            status.setText(response);
        });

        Scene removeFromPotionScene = new Scene(components);
        window.setScene(removeFromPotionScene);

        window.show();
    }

    public void generatedPotionScene(Stage window) {
        BorderPane components = new BorderPane();
        components.setPadding(padding);
        components.setBackground(background);

        Label titleLabel = uiBuilder.createSceneTitle("Generated Potion");
        components.setTop(titleLabel);

        Label nameLabel = uiBuilder.createSceneTitle(logic.tempPotion.getName());
        nameLabel.setPadding(padding);

        GridPane list = uiBuilder.createIngredientList(logic.tempPotion.getIngredients());

        VBox nameAndList = new VBox();
        nameAndList.getChildren().addAll(nameLabel, list);
        nameAndList.setAlignment(Pos.CENTER);
        nameAndList.setPadding(padding);
        components.setCenter(nameAndList);

        HBox buttons = new HBox();
        Button confirm = new Button("Save Potion to Potion Library");
        confirm.setStyle(buttonStyle);
        Button toLibrary = new Button("Go To Potion Library");
        toLibrary.setStyle(buttonStyle);
        Button cancel = new Button("Back To Create A Potion");
        cancel.setStyle(buttonStyle);
        buttons.getChildren().addAll(confirm, toLibrary, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        status.setTextFill(Color.WHITE);
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);
        buttonsAndStatus.getChildren().addAll(status, buttons);

        components.setBottom(buttonsAndStatus);

        cancel.setOnAction((event) -> {
            createAPotion(window);
        });

        confirm.setOnAction((event) -> {
            String retVal = logic.addPotionToLibrary();
            if (retVal.equals("noSpace")) {
                status.setText("No Space for new Potion.");
            } else if (retVal.equals("sameName")) {
                setNameForPotion(window, logic.tempPotion.getName());
            } else {
                status.setText("Potion saved to Potion Library.");
            }
        });

        toLibrary.setOnAction((event) -> {
            potionLibrary(window);
        });

        Scene generatedPotion = new Scene(components);

        window.setScene(generatedPotion);
        window.show();
    }

    public void setNameForPotion(Stage window, String name) {
        BorderPane components = new BorderPane();

        Label statusLabel = new Label("A potion by the name of ");
        statusLabel.setTextFill(Color.WHITE);
        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Label statusAfterLabel = new Label(" already exists.");
        statusAfterLabel.setTextFill(Color.WHITE);

        HBox nameFields = new HBox();
        nameFields.getChildren().addAll(statusLabel, nameLabel, statusAfterLabel);
        nameFields.setAlignment(Pos.CENTER);

        Label amountLabel = new Label("New Name: ");
        amountLabel.setTextFill(Color.WHITE);
        TextField amountField = new TextField();
        amountField.setStyle(buttonStyle);

        HBox amountFields = new HBox();
        amountFields.getChildren().addAll(amountLabel, amountField);
        amountFields.setSpacing(5);
        amountFields.setAlignment(Pos.CENTER);

        VBox fields = new VBox();
        fields.getChildren().addAll(nameFields, amountFields);
        fields.setSpacing(20);
        fields.setAlignment(Pos.CENTER);

        components.setCenter(fields);

        HBox buttons = new HBox();
        Button confirm = new Button("Confirm");
        confirm.setStyle(buttonStyle);
        Button cancel = new Button("Cancel");
        cancel.setStyle(buttonStyle);
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        status.setTextFill(Color.WHITE);
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);
        buttonsAndStatus.getChildren().addAll(status, buttons);

        components.setBottom(buttonsAndStatus);
        components.setPadding(padding);
        components.setBackground(background);

        cancel.setOnAction((event) -> {
            createAPotion(window);
        });

        confirm.setOnAction((event) -> {
            if (!amountField.getText().isEmpty()) {
                logic.tempPotion.setName(amountField.getText());
                String retVal = logic.addPotionToLibrary();
                String response = "";
                if (retVal.equals("sameName")) {
                    response = "A potion by this name already exists.";
                } else if (retVal.equals("noSpace")) {
                    response = "There is no space left in the potion library.";
                } else {
                    amountField.clear();
                    potionLibrary(window);
                }
                status.setText(response);
            }
        });

        Scene newNameScene = new Scene(components);
        window.setScene(newNameScene);

        window.show();
    }

    public void ingredientLibrary(Stage window) {
        BorderPane ingredientComponents = new BorderPane();

        Label titleLabel = uiBuilder.createSceneTitle("Ingredient Library");
        ingredientComponents.setTop(titleLabel);

        ObservableList<String> ingredientList = FXCollections.observableArrayList(logic.ingredientLibrary.getIngredientsNames());
        ListView<String> listView = new ListView();
        listView.setItems(ingredientList);

        HBox ingredientButtons = new HBox();
        ingredientButtons.setSpacing(20);
        Button backToMain = new Button("Back");
        backToMain.setStyle(buttonStyle);
        Button newIngredient = new Button("New Ingredient");
        newIngredient.setStyle(buttonStyle);
        Button removeIngredient = new Button("Remove Ingredient from Library");
        removeIngredient.setStyle(buttonStyle);
        ingredientButtons.getChildren().addAll(newIngredient, removeIngredient, backToMain);
        ingredientButtons.setAlignment(Pos.CENTER);
        ingredientButtons.setPadding(padding);

        backToMain.setOnAction((event) -> {
            start(window);
        });

        newIngredient.setOnAction((event) -> {
            newIngredient(window);
        });

        removeIngredient.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                logic.ingredientLibrary.removeIngredient(selected);
                ingredientLibrary(window);
            }
        });

        ingredientComponents.setCenter(listView);
        ingredientComponents.setBottom(ingredientButtons);
        ingredientComponents.setBackground(background);

        ingredientComponents.setPadding(padding);

        Scene ingredientLibrary = new Scene(ingredientComponents);
        window.setScene(ingredientLibrary);

        window.show();
    }

    public void newIngredient(Stage window) {
        BorderPane components = new BorderPane();
        components.setPadding(padding);

        GridPane fields = new GridPane();
        TextField nameField = new TextField();
        nameField.setStyle(buttonStyle);
        TextField unitField = new TextField();
        unitField.setStyle(buttonStyle);
        Label nameLabel = new Label("Name:");
        nameLabel.setTextFill(Color.WHITE);
        Label unitLabel = new Label("Unit:");
        unitLabel.setTextFill(Color.WHITE);
        fields.add(nameLabel, 0, 0);
        fields.add(unitLabel, 0, 1);
        fields.add(nameField, 1, 0);
        fields.add(unitField, 1, 1);
        fields.setHgap(10.0);
        fields.setVgap(10.0);
        fields.setAlignment(Pos.CENTER);

        components.setCenter(fields);

        HBox buttons = new HBox();
        Button confirm = new Button("Confirm");
        confirm.setStyle(buttonStyle);
        Button cancel = new Button("Back To Ingredient Library");
        cancel.setStyle(buttonStyle);
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        status.setTextFill(Color.WHITE);
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);
        buttonsAndStatus.getChildren().addAll(status, buttons);

        components.setBottom(buttonsAndStatus);
        components.setBackground(background);

        cancel.setOnAction((event) -> {
            ingredientLibrary(window);
        });

        confirm.setOnAction((event) -> {
            String ingredientName = nameField.getText();
            String ingredientUnit = unitField.getText();
            String response = "Unknown error";

            String retVal = logic.ingredientLibrary.addIngredient(ingredientName, ingredientUnit);

            if (retVal.equals("duplicate")) {
                response = "Ingredient already in library.";
            } else if (retVal.equals("fields")) {
                response = "Fill fields in.";
            } else if (retVal.equals("clear")) {
                nameField.clear();
                unitField.clear();
                response = "Ingredient added.";
            }
            status.setText(response);
        });

        Scene newIngredientScene = new Scene(components);
        window.setScene(newIngredientScene);

        window.show();
    }

    public void potionLibrary(Stage window) {
        BorderPane potionsLibComponents = new BorderPane();

        Label titleLabel = uiBuilder.createSceneTitle("Potion Library");
        potionsLibComponents.setTop(titleLabel);

        ObservableList<String> potionList = FXCollections.observableArrayList(logic.potionLibrary.getPotionsNames());
        ListView<String> listView = new ListView();
        listView.setItems(potionList);

        HBox buttons = new HBox();
        Button viewPotion = new Button("View Potion");
        viewPotion.setStyle(buttonStyle);
        Button cancel = new Button("Back To Main Menu");
        cancel.setStyle(buttonStyle);
        Button delete = new Button("Delete Potion");
        delete.setStyle(buttonStyle);
        buttons.getChildren().addAll(viewPotion, delete, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(padding);

        cancel.setOnAction((event) -> {
            start(window);
        });

        viewPotion.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                viewPotion(window, selected);
            }
        });

        delete.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                logic.potionLibrary.deletePotion(selected);
                potionLibrary(window);
            }
        });

        potionsLibComponents.setCenter(listView);
        potionsLibComponents.setBottom(buttons);
        potionsLibComponents.setPadding(padding);
        potionsLibComponents.setBackground(background);

        Scene potionLibScene = new Scene(potionsLibComponents);
        window.setScene(potionLibScene);

        window.show();
    }

    public void viewPotion(Stage window, String name) {
        BorderPane viewComponents = new BorderPane();
        viewComponents.setBackground(background);

        Label titleLabel = uiBuilder.createSceneTitle("View Potion");
        viewComponents.setTop(titleLabel);

        GridPane list = uiBuilder.createIngredientList(logic.potionLibrary.getPotionByName(name).getIngredients());

        Label nameLabel = uiBuilder.createSceneTitle(logic.potionLibrary.getPotionByName(name).getName());
        nameLabel.setPadding(padding);

        VBox nameAndList = new VBox();
        nameAndList.getChildren().addAll(nameLabel, list);
        nameAndList.setAlignment(Pos.CENTER);
        nameAndList.setPadding(padding);
        viewComponents.setCenter(nameAndList);

        HBox createButtons = new HBox();
        createButtons.setSpacing(20);
        Button backToPotionLib = new Button("Back To Potion Library");
        backToPotionLib.setStyle(buttonStyle);
        createButtons.getChildren().addAll(backToPotionLib);
        createButtons.setAlignment(Pos.CENTER);

        viewComponents.setCenter(nameAndList);
        viewComponents.setBottom(createButtons);
        viewComponents.setPadding(padding);

        backToPotionLib.setOnAction((event) -> {
            potionLibrary(window);
        });

        Scene viewPotion = new Scene(viewComponents);
        window.setScene(viewPotion);

        window.show();
    }

}
