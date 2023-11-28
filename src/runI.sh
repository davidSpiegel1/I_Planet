#!/bin/bash
javac --module-path /opt/javafx/javafx-sdk-21.0.1/lib/ --add-modules javafx.controls,javafx.fxml,javafx.media view/IplanetFX.java

java --module-path /opt/javafx/javafx-sdk-21.0.1/lib/ --add-modules javafx.controls,javafx.fxml,javafx.media view.IplanetFX
