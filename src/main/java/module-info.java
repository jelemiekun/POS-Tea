module com.example.postearevised {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;
    requires javafx.swing;


    opens com.example.postearevised to javafx.fxml;
    exports com.example.postearevised;
    exports com.example.postearevised.Models.Additional;
    opens com.example.postearevised.Models.Additional to javafx.fxml;
    exports com.example.postearevised.Controllers.Additional;
    opens com.example.postearevised.Controllers.Additional to javafx.fxml;
    exports com.example.postearevised.Controllers.Main;
    opens com.example.postearevised.Controllers.Main to javafx.fxml;
    exports com.example.postearevised.Models.Main;
    opens com.example.postearevised.Models.Main to javafx.fxml;
    opens com.example.postearevised.Objects.Products to javafx.base;
    opens com.example.postearevised.Objects.Order to javafx.base;
}