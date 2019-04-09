
package rohtoappi.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UIBuilder {
    
    
    
    public Label createSceneTitle(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        titleLabel.setPadding(new Insets(0, 10, 10, 0));
        return titleLabel;
    }
    
}
