package mos;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MainStageController {




    @FXML
    private ImageView A1 = new ImageView();

    @FXML
    private void move()
    {

        A1.setImage(Images.white_Rook);
        //A1.setLayoutX(5);

    }

}
