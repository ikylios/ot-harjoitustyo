/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rohtoappi.rohtoappi.AppLogic;

/**
 *
 * @author xilxilx
 */
public class AppUI extends Application {
    
    AppLogic logic;
    Insets padding = new Insets(10, 10, 10, 10); 
    
    public void giveLogic(AppLogic logic) {
        this.logic = logic;
    }
    
    @Override
    public void start(Stage window) {                                                       
        
        Button createButton = new Button("Create A Potion");
        Button browseButton = new Button("Potion Library");
        Button exitButton = new Button("Exit");
                
        VBox mainButtons = new VBox();
        mainButtons.setAlignment(Pos.CENTER);
        mainButtons.setSpacing(20);
        mainButtons.getChildren().addAll(createButton, browseButton, exitButton);         
        
        Scene main = new Scene(mainButtons);
        
        
        exitButton.setOnAction((event) -> {
            stop();
        });
        
        createButton.setOnAction((event) -> {            
            createPotion(window);            
        });
        
                
        window.setScene(main);
        window.show();
        
    }
    
    public void stop() {
        Platform.exit();
    }
    
    public void createPotion(Stage window) {                
        
        BorderPane createComponents = new BorderPane();        
        
        
        GridPane ingredientGrid = new GridPane();
//        for (int i = 0; i < ingredientList.size(); i++) {
//          lisää nappuloita respectively    
//        }
        
        HBox createButtons = new HBox();
        createButtons.setSpacing(10);        
        Button confirmPotion = new Button("Confirm potion");
        Button backToMenu = new Button("Cancel");
        Button randomisePotion = new Button("Randomize");
        createButtons.getChildren().addAll(confirmPotion, backToMenu, randomisePotion);
        createButtons.setAlignment(Pos.CENTER);
        
        VBox ingredientButtons = new VBox();
        ingredientButtons.setSpacing(20);
        Button addIngredient = new Button("Add ingredient (ingredient library)");
        Button removeIngredient = new Button("Remove ingredient");
        ingredientButtons.getChildren().addAll(addIngredient, removeIngredient);
        
        createComponents.setCenter(ingredientGrid);
        createComponents.setBottom(createButtons);
        createComponents.setRight(ingredientButtons);                
        
        //MISSING: CONFIRM, RANDOMISE        
        backToMenu.setOnAction((event) -> {
            start(window);
        });
        
        //MISSING: REMOVE
        addIngredient.setOnAction((event) -> {
            ingredientLibrary(window);
        });
        
        Scene createPotion = new Scene(createComponents);
        window.setScene(createPotion);
        
        window.show();
        
        
    }
    
    public void ingredientLibrary(Stage window) {
        
        BorderPane ingredientComponents = new BorderPane();                                    

        ObservableList<String> ingredientList = FXCollections.observableArrayList(logic.ingredientLibrary.getIngredientsNames());
        ListView<String> listView = new ListView();
        listView.setItems(ingredientList);                
        
        HBox ingredientButtons = new HBox();
        ingredientButtons.setSpacing(20);
        Button backToPotion = new Button("Cancel");
        Button addIngredient = new Button("Add Ingredient to Potion");
        Button newIngredient = new Button("New Ingredient");
        ingredientButtons.getChildren().addAll(backToPotion, newIngredient, addIngredient);
        ingredientButtons.setAlignment(Pos.CENTER);
        
        backToPotion.setOnAction((event) -> {
            createPotion(window);
        });
        
        newIngredient.setOnAction((event) -> {
            newIngredient(window);
        });
        
        ingredientComponents.setCenter(listView);
        ingredientComponents.setBottom(ingredientButtons);        
        
        Scene ingredientLibrary = new Scene(ingredientComponents);
        window.setScene(ingredientLibrary);
        
        window.show();
        
    }
    
    public void newIngredient(Stage window) {
        
        BorderPane components = new BorderPane();
        
        GridPane fields = new GridPane();                        
        TextField nameField = new TextField();
        TextField unitField = new TextField();
        Label nameLabel = new Label("Name:");
        Label unitLabel = new Label("Unit:");
        fields.add(nameLabel, 0, 0);
        fields.add(unitLabel, 0, 1);
        fields.add(nameField, 1, 0);
        fields.add(unitField, 1, 1);
        
        components.setCenter(fields);
        
        HBox buttons = new HBox();        
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Back To Ingredient Library");
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        
        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
        buttonsAndStatus.setSpacing(10);
        buttonsAndStatus.setAlignment(Pos.CENTER);        
        buttonsAndStatus.getChildren().addAll(status, buttons);
        
        components.setBottom(buttonsAndStatus);
        
        cancel.setOnAction((event) -> {
            ingredientLibrary(window);
        });
        
        confirm.setOnAction((event) -> {
            String ingredientName = nameField.getText();
            String ingredientUnit = unitField.getText();
            String retVal = logic.ingredientLibrary.addIngredient(ingredientName, ingredientUnit);
            if (retVal.equals("duplicate")) {
                status.setText("Ingredient already in library.");
            } else if (retVal.equals("fields")) {
                status.setText("Fill fields in.");
            } else if (retVal.equals("clear")) {
                    nameField.clear();
                    unitField.clear();
                    status.setText("Ingredient added.");
            } else {
                status.setText("Unknown error.");
            }
        });    

        
        Scene newIngredientScene = new Scene(components);
        window.setScene(newIngredientScene);
        
        window.show();        
    }

    
}
