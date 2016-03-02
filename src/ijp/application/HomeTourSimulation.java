/*
 * The MIT License
 *
 * Copyright 2015 Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ijp.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */
public class HomeTourSimulation extends Application {

	/**
	 * The main FXML used.
	 */
	public static final String worldViewPathFXML = "/ijp/view/WorldView.fxml";

	private Parent root;
	private Scene scene;

	/**
	 * Start the viewer on the specified stage.
	 *
	 * @param stage
	 *            the JavaFx stage for the application.
	 */
	public void start(Stage stage) {

		try {

			root = FXMLLoader.load(getClass().getResource(worldViewPathFXML));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Home Tour");
			stage.show();

		} catch (IOException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			Platform.exit();
		}
	}

	/**
	 * Start the application
	 *
	 * @param args
	 *            the command line arguments.
	 */
	public static void main(String args[]) {
		launch(args);
	}
}
