///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rohtoappi.ui;
//
//import java.util.ArrayList;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//import rohtoappi.domain.components.Ingredient;
//
///**
// *
// * @author xilxilx
// */
//public class PotionUI {
//    
//    public void createAPotion(Stage window) {                       
//        
//        BorderPane createComponents = new BorderPane();     
//        
//        Label titleLabel = uiBuilder.createSceneTitle("Create A Potion");        
//        
//        createComponents.setTop(titleLabel);
//                
//        GridPane ingredientGrid = createIngredientGrid(logic.tempPotion.getIngredients());
//        ingredientGrid.setAlignment(Pos.TOP_CENTER);
//                
//        HBox createButtons = new HBox();
//        createButtons.setSpacing(10);        
//        Button confirmPotion = new Button("Confirm potion");
//        Button backToMenu = new Button("Cancel");
//        Button randomisePotion = new Button("Randomize");
//        createButtons.getChildren().addAll(confirmPotion, backToMenu, randomisePotion);
//        createButtons.setAlignment(Pos.CENTER);
//        
//        VBox ingredientButtons = new VBox();
//        ingredientButtons.setSpacing(20);
//        Button addIngredient = new Button("Add ingredient (ingredient library)");
//        Button removeIngredient = new Button("Remove ingredient");
//        ingredientButtons.getChildren().addAll(addIngredient, removeIngredient);
//        
//        createComponents.setCenter(ingredientGrid);
//        createComponents.setBottom(createButtons);
//        createComponents.setRight(ingredientButtons);
//        
//        createComponents.setPadding(padding);
//        
//        //MISSING: CONFIRM, RANDOMISE        
//        backToMenu.setOnAction((event) -> {
//            //add warning: will delete the current potion
//            logic.tempPotion.emptyPotion();
//            start(window);
//        });
//        
//        confirmPotion.setOnAction((event) -> {
//            logic.tempPotion.generateMagic();
//            generatedPotionScene(window);
//        });
//        
//        randomisePotion.setOnAction((event) -> {
////            Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
////            logic.addToTempPotion(ingredient.getName(), ingredient.getMeasuringUnit());
//        });
//                
//        addIngredient.setOnAction((event) -> {            
//            ingredientLibrary(window);
//        });
//        
//        removeIngredient.setOnAction((event) -> {
//            removeFromPotion(window);
//        });
//        
//        Scene createAPotion = new Scene(createComponents);
//        window.setScene(createAPotion);
//        
//        window.show();       
//        
//    }
//    
//    public GridPane createIngredientGrid(ArrayList<Ingredient> ingredients) {
//        GridPane ingredientGrid = new GridPane(); 
//        if (ingredients.isEmpty()) {
//            ingredientGrid.add(new Label("No ingredients. Empty. Nada."), 0, 0);
//        } else {
//            int row = 0;        
//            for (Ingredient ingredient : ingredients) {
//                int column = 0;
//    //            Label nameLabel = new Label(ingredient.getName() + "\t\t");
//    //            nameLabel.setText();
//                ingredientGrid.add(new Label(ingredient.getName() + "\t\t"), column, row);
//                column++;
//                ingredientGrid.add(new Label("" + ingredient.getAmount() + " " + ingredient.getMeasuringUnit()), column, row);
//                column++;
//                ingredientGrid.add(new Button("+"), column, row);
//                column++;
//                ingredientGrid.add(new Button(" - "), column, row);
//                row++;
//            }
//        }
//        ingredientGrid.setAlignment(Pos.TOP_CENTER);
//        ingredientGrid.setHgap(10);
//        ingredientGrid.setVgap(10);
//        return ingredientGrid;        
//    }        
//    
//    public void addIngredientToPotion(Stage window, String name) {
//        BorderPane components = new BorderPane();
//                                             
//        TextField amountField = new TextField();        
//        Label amountLabel = new Label("Amount: ");
//        String unit = logic.ingredientLibrary.getIngredientByName(name).getMeasuringUnit();
//        Label unitLabel = new Label(" " + unit);
//        
//        HBox amountFields = new HBox();
//        amountFields.getChildren().addAll(amountLabel, amountField, unitLabel);
//        amountFields.setSpacing(5);        
//        amountFields.setAlignment(Pos.BOTTOM_LEFT);
//        
//        Label addingLabel = new Label("Adding ingredient ");
//        Label nameLabel = new Label(name);
//        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));        
//        
//        HBox nameFields = new HBox();
//        nameFields.getChildren().addAll(addingLabel, nameLabel);        
//        
//        VBox fields = new VBox();
//        fields.getChildren().addAll(nameFields, amountFields);
//        fields.setSpacing(20);
//        
//        components.setCenter(fields);
//        
//        HBox buttons = new HBox();        
//        Button confirm = new Button("Confirm");
//        Button cancel = new Button("Back To Ingredient Library");
//        buttons.getChildren().addAll(confirm, cancel);
//        buttons.setSpacing(20);
//        buttons.setAlignment(Pos.CENTER);
//        
//        VBox buttonsAndStatus = new VBox();
//        Label status = new Label();
//        buttonsAndStatus.setSpacing(10);        
//        buttonsAndStatus.getChildren().addAll(status, buttons);
//        buttonsAndStatus.setAlignment(Pos.CENTER);        
//        
//        components.setBottom(buttonsAndStatus);
//        components.setPadding(padding);
//        
//        cancel.setOnAction((event) -> {
//            ingredientLibrary(window);
//        });
//        
//        confirm.setOnAction((event) -> {         
//            if (!amountField.getText().isEmpty()) {
//                String retVal = logic.addToTempPotion(name, amountField.getText());
//                String response = "";
//                if (retVal.equals("ingredientPresent")) {
//                    response = "Ingredient is already in potion.";
//                } else if (retVal.equals("amountIsNotInteger")) {
//                    response = "Not a valid value.";
//                } else {
//                    amountField.clear();
//                    ingredientLibrary(window);
//                }
//                status.setText(response);
//            }                        
//        });    
//        
//        Scene addIngredientScene = new Scene(components);
//        window.setScene(addIngredientScene);
//        
//        window.show();        
//    }
//    
//    public void removeFromPotion(Stage window) {
//        BorderPane components = new BorderPane();
//        
//        HBox fields = new HBox();                              
//        TextField nameField = new TextField();        
//        Label nameLabel = new Label("Name:");        
//        fields.getChildren().addAll(nameLabel, nameField);        
//        
//        components.setCenter(fields);
//        
//        HBox buttons = new HBox();        
//        Button confirm = new Button("Confirm");
//        Button cancel = new Button("Back To Create A Potion");
//        buttons.getChildren().addAll(confirm, cancel);
//        buttons.setSpacing(20);
//        buttons.setAlignment(Pos.CENTER);
//        
//        VBox buttonsAndStatus = new VBox();
//        Label status = new Label();
//        buttonsAndStatus.setSpacing(10);
//        buttonsAndStatus.setAlignment(Pos.CENTER);        
//        buttonsAndStatus.getChildren().addAll(status, buttons);
//        
//        components.setBottom(buttonsAndStatus);
//        
//        cancel.setOnAction((event) -> {
//            createAPotion(window);
//        });
//        
//        confirm.setOnAction((event) -> {
//            String ingredientName = nameField.getText();            
//            String response = "Unknown Error.";            
//            String retVal = logic.tempPotion.removeFromPotion(ingredientName);            
//            if (retVal.equals("clear")) {
//                nameField.clear();
//                response = "Ingredient deleted.";
//            } else if (retVal.equals("notInPotion")) {
//                response = "Ingredient is not in Potion.";
//            } else if (retVal.equals("invalidValue")) {
//                response = "Not a valid value.";
//            }
//            
//            status.setText(response);
//        });    
//        
//        Scene removeFromPotionScene = new Scene(components);
//        window.setScene(removeFromPotionScene);
//        
//        window.show();        
//    }
//    
//    public void generatedPotionScene(Stage window) {
//        BorderPane components = new BorderPane();
//        components.setPadding(padding);
//        
//        Label titleLabel = uiBuilder.createSceneTitle("Generated Potion");
//        components.setTop(titleLabel);
//                   
//        Label nameLabel = uiBuilder.createSceneTitle(logic.tempPotion.getName());
//        nameLabel.setPadding(padding);
//        
//        GridPane list = uiBuilder.createIngredientList(logic.tempPotion.getIngredients());                
//        
//        VBox nameAndList = new VBox();
//        nameAndList.getChildren().addAll(nameLabel, list);
//        nameAndList.setAlignment(Pos.CENTER);  
//        nameAndList.setPadding(padding);
//        components.setCenter(nameAndList);
//        
//        HBox buttons = new HBox();        
//        Button confirm = new Button("Save Potion to Potion Library");
//        Button cancel = new Button("Back To Create A Potion");
//        buttons.getChildren().addAll(confirm, cancel);
//        buttons.setSpacing(20);
//        buttons.setAlignment(Pos.CENTER);
//        
//        VBox buttonsAndStatus = new VBox();
//        Label status = new Label();
//        buttonsAndStatus.setSpacing(10);
//        buttonsAndStatus.setAlignment(Pos.CENTER);        
//        buttonsAndStatus.getChildren().addAll(status, buttons);
//        
//        components.setBottom(buttonsAndStatus);
//        
//        cancel.setOnAction((event) -> {
//            createAPotion(window);
//        });
//        
//        confirm.setOnAction((event) -> {
//                                    
//        });
//        
//        Scene generatedPotion = new Scene(components);        
//        
//        window.setScene(generatedPotion);
//        window.show();
//    }           
//    
//    
//    
//}
