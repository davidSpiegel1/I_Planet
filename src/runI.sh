javac --module-path $HOME/Documents/java/javafx-sdk-17.0.8/lib/ --add-modules javafx.controls,javafx.fxml,javafx.media view/IplanetFX.java

java --module-path $HOME/Documents/java/javafx-sdk-17.0.8/lib/ --add-modules javafx.controls,javafx.fxml,javafx.media view.IplanetFX

rm model/*.class
rm view/*.class
rm controller/*.class
