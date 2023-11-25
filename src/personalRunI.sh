#!/bin/bash
javac --module-path $HOME/Downloads/Important_Insta/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media view/IplanetFX.java

java --module-path $HOME/Downloads/Important_Insta/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media view.IplanetFX

rm model/*.class
rm view/*.class
rm controller/*.class
