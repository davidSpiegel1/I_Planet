#!/bin/bash
javac --module-path $HOME/Documents/java/javafx-sdk-17.0.8/lib/ --add-modules javafx.controls,javafx.fxml view/IplanetFX.java

java --module-path $HOME/Documents/java/javafx-sdk-17.0.8/lib/ --add-modules javafx.controls,javafx.fxml view.IplanetFX
