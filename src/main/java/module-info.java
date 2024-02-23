module com.example.postearevised {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.postearevised to javafx.fxml;
    exports com.example.postearevised;
    exports com.example.postearevised.Controllers;
    opens com.example.postearevised.Controllers to javafx.fxml;
    exports com.example.postearevised.Models;
    opens com.example.postearevised.Models to javafx.fxml;
    exports com.example.postearevised.Controllers.Additional;
    opens com.example.postearevised.Controllers.Additional to javafx.fxml;
}