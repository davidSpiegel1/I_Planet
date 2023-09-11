#!/bin/bash
javac --module-path /Users/davidspiegel/Downloads/Important_Insta/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml view/IplanetFX.java

java --module-path /Users/davidspiegel/Downloads/Important_Insta/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml view.IplanetFX
