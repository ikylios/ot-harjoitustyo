
package rohtoappi.ui;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import rohtoappi.domain.components.Ingredient;

public class UIBuilder {
    
    public Insets padding;
    
    public Label createSceneTitle(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        titleLabel.setPadding(new Insets(0, 10, 10, 0));
        return titleLabel;
    }
    
    public Scene removeFrom() {
        BorderPane components = new BorderPane();
        
        Scene removeScene = new Scene(components);
        
        return removeScene;
    }
    
    public GridPane createIngredientList(ArrayList<Ingredient> ingredients) {
        GridPane grid = new GridPane();
        
        int row = 0;        
        for (Ingredient ingredient : ingredients) {
            int column = 0;
            grid.add(new Label(ingredient.getName()), column, row);
            column++;
            grid.add(new Label("\t\t" + ingredient.getAmount()+ " " + ingredient.getMeasuringUnit()), column, row);
            row++;
        }
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
    
    
    
}
