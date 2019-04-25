
package rohtoappi.ui;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import rohtoappi.domain.components.Ingredient;

public class UIBuilder {
    
    public Insets padding = new Insets(0, 10, 10, 0);
    public String buttonStyle = "-fx-background-color: #8872a5; -fx-text-fill: #ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;";
    
    public Label createSceneTitle(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setPadding(padding);
        return titleLabel;
    }
    
//    public Scene removeFrom() {
//        BorderPane components = new BorderPane();
//        
//        Scene removeScene = new Scene(components);
//        
//        return removeScene;
//    }
    
    public GridPane createIngredientList(ArrayList<Ingredient> ingredients) {
        GridPane grid = new GridPane();
        
        int row = 0;        
        for (Ingredient ingredient : ingredients) {
            int column = 0;
            Label labelName = new Label(ingredient.getName());
            labelName.setTextFill(Color.WHITE);
            grid.add(labelName, column, row);
            column++;
            Label numberLabel = new Label("\t\t" + ingredient.getAmount()+ " " + ingredient.getMeasuringUnit());
            numberLabel.setTextFill(Color.WHITE);
            grid.add(numberLabel, column, row);
            row++;
        }
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
    
    public GridPane createIngredientGrid(ArrayList<Ingredient> ingredients) {
        GridPane ingredientGrid = new GridPane(); 
        if (ingredients.isEmpty()) {
            Label emptyLabel = new Label("No ingredients. Empty. Nada.");
            emptyLabel.setTextFill(Color.WHITE);
            ingredientGrid.add(emptyLabel, 0, 0);
        } else {
            int row = 0;        
            for (Ingredient ingredient : ingredients) {
                int column = 0;
                Label nameLabel = new Label(ingredient.getName() + "\t\t");
                nameLabel.setTextFill(Color.WHITE);
                ingredientGrid.add(nameLabel, column, row);
                column++;
                Label numbersLabel = new Label("" + ingredient.getAmount() + " " + ingredient.getMeasuringUnit());
                numbersLabel.setTextFill(Color.WHITE);
                ingredientGrid.add(numbersLabel, column, row);
                column++;
//                Button addButton = new Button("+");
//                ingredientGrid.add(new Button("+"), column, row);
//                column++;                
//                ingredientGrid.add(new Button(" - "), column, row);
                row++;
            }
        }
        ingredientGrid.setAlignment(Pos.TOP_CENTER);
        ingredientGrid.setHgap(10);
        ingredientGrid.setVgap(10);
        return ingredientGrid;        
    }
        
    
}
