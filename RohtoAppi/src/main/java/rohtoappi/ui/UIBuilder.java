package rohtoappi.ui;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

    public GridPane createIngredientList(ArrayList<Ingredient> ingredients) {
        GridPane grid = new GridPane();

        int row = 0;
        for (Ingredient ingredient : ingredients) {
            int column = 0;
            Label labelName = new Label(ingredient.getName());
            labelName.setTextFill(Color.WHITE);
            grid.add(labelName, column, row);
            column++;
            Label numberLabel = new Label("\t\t" + ingredient.getAmount() + " " + ingredient.getMeasuringUnit());
            numberLabel.setTextFill(Color.WHITE);
            grid.add(numberLabel, column, row);
            row++;
        }
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

}
