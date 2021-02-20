import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.stage.StageStyle.UNDECORATED;

public class EncounterPopup extends Stage {

    public EncounterPopup(int encounterNum) {

        if (encounterNum == 0) {
            setScene(PirateScreen.generatePirateScreen());
        } else if (encounterNum == 1) {
            setScene(TraderScreen.generateTraderScreen());
        } else {
            setScene(MarineScreen.generateMarineScreen());
        }

        this.initStyle(UNDECORATED);
        this.setHeight(300);
        this.setWidth(600);

        this.initModality(Modality.APPLICATION_MODAL);
        showAndWait();

    }


}
