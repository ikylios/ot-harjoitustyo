/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author xilxilx
 */
public class IngredientLibraryUI {
    
//    
//    
//    public void ingredientLibrary(Stage window) {
//        
//        BorderPane ingredientComponents = new BorderPane();                                    
//
//        ObservableList<String> ingredientList = FXCollections.observableArrayList(logic.ingredientLibrary.getIngredientsNames());
//        ListView<String> listView = new ListView();
//        listView.setItems(ingredientList);        
//        
//        HBox ingredientButtons = new HBox();
//        ingredientButtons.setSpacing(20);
//        Button backToPotion = new Button("Back");
//        Button addIngredient = new Button("Add to Potion");
//        Button newIngredient = new Button("New Ingredient");
//        Button removeIngredient = new Button("Remove Ingredient from Library");
//        ingredientButtons.getChildren().addAll(addIngredient, backToPotion, newIngredient, removeIngredient);
//        ingredientButtons.setAlignment(Pos.CENTER);        
//        
//        backToPotion.setOnAction((event) -> {
//            createPotion(window);
//        });
//        
//        newIngredient.setOnAction((event) -> {
//            newIngredient(window);
//        });
//        
//        removeIngredient.setOnAction((event) -> {
//            String selected = listView.getSelectionModel().getSelectedItem().toString();
//            if (!selected.isEmpty()) {
//                logic.removeFromTempPotion(selected);                
//            }                              
//        });                
//        
//        addIngredient.setOnAction((event) -> {
//            String selected = listView.getSelectionModel().getSelectedItem().toString();
//            if (!selected.isEmpty()) {
//                addIngredientToPotion(window, selected);
//            }                                                      
//        });
//        
//        ingredientComponents.setCenter(listView);
//        ingredientComponents.setBottom(ingredientButtons);        
//        
//        ingredientComponents.setPadding(padding);
//        
//        Scene ingredientLibrary = new Scene(ingredientComponents);
//        window.setScene(ingredientLibrary);
//        
//        window.show();        
//    }
//    
}
