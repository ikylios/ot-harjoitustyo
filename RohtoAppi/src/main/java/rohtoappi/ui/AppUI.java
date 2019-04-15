/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.ui;

import java.util.ArrayList;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;

/**
 *
 * @author xilxilx
 */
public class AppUI extends Application {
    
    AppLogic logic;
    Insets padding;
    UIBuilder uiBuilder;
//    PotionUI potionUI;
    
    public void giveLogic(AppLogic logic) {        
        this.logic = logic;
        padding = new Insets(20, 20, 20, 20);
        uiBuilder = new UIBuilder();
//        potionUI = new PotionUI();
    }
    
    @Override
    public void start(Stage window) {                                                       
        
        Button createButton = new Button("Create A Potion");
        createButton.setMaxWidth(200.0);
        Button potionLibButton = new Button("Potion Library");
        potionLibButton.setMaxWidth(200.0);        
        Button exitButton = new Button("Exit");        
                
        VBox mainButtons = new VBox();
        mainButtons.setAlignment(Pos.CENTER);
        mainButtons.setPrefWidth(150.0);
        mainButtons.setSpacing(20);
        mainButtons.getChildren().addAll(createButton, potionLibButton, exitButton);         
        
        Scene main = new Scene(mainButtons);
                
        exitButton.setOnAction((event) -> {
            stop();
        });
        
        createButton.setOnAction((event) -> {            
//            potionUI.createAPotion(window);            
            createAPotion(window);            
        });
        
        potionLibButton.setOnAction((event) -> {
            potionLibrary(window);
        });
                        
        window.setScene(main);
        window.show();
        
    }
    
    public void stop() {
        logic.ingredientLibrary.writeToFile(logic.ingredientLibrary.getIngredients());
        Platform.exit();
    }
    
    
    
    public void createAPotion(Stage window) {                               
        BorderPane createComponents = new BorderPane();     
        
        Label titleLabel = uiBuilder.createSceneTitle("Create A Potion");                
        createComponents.setTop(titleLabel);
                
        GridPane ingredientGrid = uiBuilder.createIngredientGrid(logic.tempPotion.getIngredients());
        ingredientGrid.setAlignment(Pos.TOP_CENTER);
       
        
//        addButton.setOnAction((event) -> {
//            logic.ingredientLibrary.getIngredientByName();            
//            
//        });
                
        HBox createButtons = new HBox();
        createButtons.setSpacing(10);        
        Button confirmPotion = new Button("Confirm Potion");
        Button backToMenu = new Button("Back To Main Menu");
        Button randomisePotion = new Button("Randomize");
        Button clearPotion = new Button("Clear Potion");
        createButtons.getChildren().addAll(confirmPotion, backToMenu, randomisePotion, clearPotion);
        createButtons.setAlignment(Pos.CENTER);
        if (logic.tempPotion.getIngredients().isEmpty()) {
            confirmPotion.setDisable(true);
        }
        
        VBox ingredientButtons = new VBox();
        ingredientButtons.setSpacing(20);
        Button addIngredient = new Button("Add ingredient (ingredient library)");
        Button removeIngredient = new Button("Remove ingredient");
        ingredientButtons.getChildren().addAll(addIngredient, removeIngredient);
        
        createComponents.setCenter(ingredientGrid);
        createComponents.setBottom(createButtons);
        createComponents.setRight(ingredientButtons);        
        createComponents.setPadding(padding);
                      
        backToMenu.setOnAction((event) -> {                        
            start(window);
        });
        
        confirmPotion.setOnAction((event) -> {
            logic.tempPotion.generateMagic();
            generatedPotionScene(window);
        });
        
        randomisePotion.setOnAction((event) -> {

        });
        randomisePotion.setDisable(true);
                
        addIngredient.setOnAction((event) -> {            
            ingredientLibrary(window);
        });
        
        removeIngredient.setOnAction((event) -> {
            removeFromPotion(window);
        });
        
        clearPotion.setOnAction((event) -> {
            logic.clearTempPotion();
            createAPotion(window);
        });
        
        if (logic.tempPotion.ingredients.isEmpty()) {
            removeIngredient.setDisable(false);
        }
        
        Scene createAPotion = new Scene(createComponents);
        window.setScene(createAPotion);
        
        window.show();       
        
    }       
    
    public void addIngredientToPotion(Stage window, String name) {
        BorderPane components = new BorderPane();
                                             
        TextField amountField = new TextField();        
        Label amountLabel = new Label("Amount: ");
        String unit = logic.ingredientLibrary.getIngredientByName(name).getMeasuringUnit();
        Label unitLabel = new Label(" " + unit);
        
        HBox amountFields = new HBox();
        amountFields.getChildren().addAll(amountLabel, amountField, unitLabel);
        amountFields.setSpacing(5);        
        amountFields.setAlignment(Pos.BOTTOM_LEFT);
        
        Label addingLabel = new Label("Adding ingredient ");
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));        
        
        HBox nameFields = new HBox();
        nameFields.getChildren().addAll(addingLabel, nameLabel);        
        
        VBox fields = new VBox();
        fields.getChildren().addAll(nameFields, amountFields);
        fields.setSpacing(20);
        
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
        buttonsAndStatus.getChildren().addAll(status, buttons);
        buttonsAndStatus.setAlignment(Pos.CENTER);        
        
        components.setBottom(buttonsAndStatus);
        components.setPadding(padding);
        
        cancel.setOnAction((event) -> {
            ingredientLibrary(window);
        });
        
        confirm.setOnAction((event) -> {         
            if (!amountField.getText().isEmpty()) {
                String retVal = logic.addToTempPotion(name, amountField.getText());
                String response = "";
                if (retVal.equals("ingredientPresent")) {
                    response = "Ingredient is already in potion.";
                } else if (retVal.equals("amountIsNotInteger")) {
                    response = "Not a valid value.";
                } else if (retVal.equals("limit")) {
                    response = "Please limit the amount to 9 digits or less.";
                } else {
                    amountField.clear();
                    ingredientLibrary(window);
                }
                status.setText(response);
            }                        
        });    
        
        Scene addIngredientScene = new Scene(components);
        window.setScene(addIngredientScene);
        
        window.show();        
    }
    
    public void removeFromPotion(Stage window) {
        BorderPane components = new BorderPane();
        
        HBox fields = new HBox();                              
        TextField nameField = new TextField();        
        Label nameLabel = new Label("Name:");        
        fields.getChildren().addAll(nameLabel, nameField);        
        
        components.setCenter(fields);
        
        HBox buttons = new HBox();        
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Back To Create A Potion");
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
            createAPotion(window);
        });
        
        confirm.setOnAction((event) -> {
            String ingredientName = nameField.getText();            
            String response = "Unknown Error.";            
            String retVal = logic.tempPotion.removeFromPotion(ingredientName);            
            if (retVal.equals("clear")) {
                nameField.clear();
                response = "Ingredient deleted.";
            } else if (retVal.equals("notInPotion")) {
                response = "Ingredient is not in Potion.";
            } else if (retVal.equals("invalidValue")) {
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
        Button toLibrary = new Button("Go To Potion Library");
        Button cancel = new Button("Back To Create A Potion");
        buttons.getChildren().addAll(confirm, toLibrary, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);        
        
        VBox buttonsAndStatus = new VBox();
        Label status = new Label();
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
                status.setText("A Potion with this name is saved in the Potion Library.");
//                setNameForPotion(window);
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
    
    public void setNameForPotion(Stage window) {
        
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
        Button backToPotion = new Button("Back");
        Button addIngredient = new Button("Add to Potion");
        Button newIngredient = new Button("New Ingredient");
        Button removeIngredient = new Button("Remove Ingredient from Library");
        ingredientButtons.getChildren().addAll(addIngredient, backToPotion, newIngredient, removeIngredient);
        ingredientButtons.setAlignment(Pos.CENTER);        
        
        backToPotion.setOnAction((event) -> {
            createAPotion(window);
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
        
        addIngredient.setOnAction((event) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                addIngredientToPotion(window, selected);
            }                                                      
        });
        
        ingredientComponents.setCenter(listView);
        ingredientComponents.setBottom(ingredientButtons);        
        
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
        TextField unitField = new TextField();
        Label nameLabel = new Label("Name:");
        Label unitLabel = new Label("Unit:");
        fields.add(nameLabel, 0, 0);
        fields.add(unitLabel, 0, 1);
        fields.add(nameField, 1, 0);
        fields.add(unitField, 1, 1);
        fields.setHgap(10.0);
        fields.setVgap(10.0);
        
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
    
    public void removeIngredient(Stage window) {        
        BorderPane components = new BorderPane();
        components.setPadding(padding);
        
        HBox fields = new HBox();                              
        TextField nameField = new TextField();        
        Label nameLabel = new Label("Name:");        
        fields.getChildren().addAll(nameLabel, nameField);        
        
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
            boolean retVal = logic.ingredientLibrary.removeIngredient(ingredientName);
            String response = "Ingredient does not exist.";
            if (retVal) {
                nameField.clear();
                response = "Ingredient deleted.";
            }          
            status.setText(response);
        });    
        
        Scene removeIngredientScene = new Scene(components);
        window.setScene(removeIngredientScene);
        
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
        Button viewPotion = new Button("View potion");
        Button cancel = new Button("Back To Main Menu");
        buttons.getChildren().addAll(viewPotion, cancel);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        
        viewPotion.setDisable(true);
        
        cancel.setOnAction((event) -> {
            start(window);
        });
        
        potionsLibComponents.setCenter(listView);
        potionsLibComponents.setBottom(buttons);
        potionsLibComponents.setPadding(padding);
        
        Scene potionLibScene = new Scene(potionsLibComponents);
        window.setScene(potionLibScene);
        
        window.show();
    }

    
}
