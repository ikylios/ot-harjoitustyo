/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.rohtoappi;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import rohtoappi.ui.AppUI;

/**
 *
 * @author xilxilx
 */
public class Main extends Application {
    
    AppUI ui;
    AppLogic logic;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        ui = new AppUI();
        logic = new AppLogic();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(700.0);
        stage.setHeight(450.0);
        stage.setTitle("PotionApp");
        ui.giveLogic(logic);
        ui.start(stage);
    }
    
    
    
}
