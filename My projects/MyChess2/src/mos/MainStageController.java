package mos;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainStageController {


    Image black_Rook = new Image("file:Black_Rook.png");

    @FXML
    private ImageView A1 = new ImageView(black_Rook);

    @FXML
    private void move()
    {
        //A1.setImage(null);
        //A1.setLayoutX(5);
    }

}
