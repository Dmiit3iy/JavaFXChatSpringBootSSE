module org.dmiit3iy {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires retrofit2.converter.jackson;
    requires java.prefs;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens org.dmiit3iy to javafx.fxml;
    opens org.dmiit3iy.сontroller to javafx.fxml;
    exports org.dmiit3iy;
    exports org.dmiit3iy.dto to com.fasterxml.jackson.databind;
    exports org.dmiit3iy.model to com.fasterxml.jackson.databind;

}
