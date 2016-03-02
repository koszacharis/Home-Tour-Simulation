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
package ijp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import ijp.inventory.Inventory;
import ijp.inventory.Item;
import ijp.location.HomeLocation;
import ijp.picture.Picture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * A controller for the HomeViewr application, which uses events to rotate and
 * move to different locations.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */

public class WorldController implements Initializable, Controller {

	@FXML
	private ImageView imageView;
	@FXML
	private ImageView itemHolder;
	@FXML
	private ImageView itemHolder1;
	@FXML
	private ImageView itemHolder2;
	@FXML
	private ImageView itemHolder3;
	@FXML
	private ImageView treeView;
	@FXML
	private ImageView helmetView;
	@FXML
	private ImageView guitarView;
	@FXML
	private ImageView basketView;
	@FXML
	private ImageView mapView;
	@FXML
	private ImageView rightArrowView;
	@FXML
	private ImageView leftArrowView;
	@FXML
	private ImageView arrowView;
	@FXML
	private ImageView posView;
	@FXML
	private ImageView exitView;
	@FXML
	private ImageView randomView;
	@FXML
	private Button buttonLeft;
	@FXML
	private Button buttonRight;
	@FXML
	private Button buttonArrow;
	@FXML
	private Button buttonExit;
	@FXML
	private Button randomButton;
	@FXML
	private TextArea output;

	private Inventory inventory = new Inventory();
	private Picture picture = new Picture();
	private HomeLocation homeLocation = new HomeLocation();

	// create new objects type Item for each new item
	private Item tree = new Item("TreeSide", "TreeRoom");
	private Item basket = new Item("BasketSide", "BasketRoom");
	private Item guitar = new Item("GuitarSide", "GuitarRoom");
	private Item helmet = new Item("HelmetSide", "HelmetRoom");

	/**
	 * Initializes the controller class. Setting images to ImageViews, through
	 * storedPictures HashMap.
	 * 
	 * @param location
	 *            URL location
	 * @param resources
	 *            ResourceBundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// load all images from HashMap.
			picture.pictureReader();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// picture.jarPictureLoader();
		initializeImages();
		moveWithButtons();
		clickItems();
		checkItemsPosition();
		consoleOutput();
		output.setEditable(false);
	}

	/**
	 * goForward is triggered when the top arrow is pressed. It is used to
	 * change room depending the location, when pressed.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	@Override
	public void goForward(ActionEvent event) {
		homeLocation.goForward();

		homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());
		topArrowVisibility();

		imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
		homeLocation.changeMapPointer(posView);
		checkItemsPosition();
		System.out.println("Moving forward to the " + homeLocation.getRoom());
		System.out.println("-------------------------------------------------");

	}

	/**
	 * Drop the item Tree.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void dropTree(ActionEvent event) {
		dropTree();
	}

	/**
	 * Pick up the item Tree.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void pickUpTree(ActionEvent event) {
		pickUpTree();

	}

	/**
	 * Drop the item Helmet.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void dropHelmet(ActionEvent event) {
		dropHelmet();
	}

	/**
	 * Pick up the item Helmet.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void pickUpHelmet(ActionEvent event) {
		pickUpHelmet();

	}

	/**
	 * Drop the item Guitar.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void dropGuitar(ActionEvent event) {
		dropGuitar();
	}

	/**
	 * Pick up the item Guitar.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void pickUpGuitar(ActionEvent event) {
		pickUpGuitar();
	}

	/**
	 * Drop the item Basket.
	 *
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void dropBasket(ActionEvent event) {
		dropBasket();
	}

	/**
	 * Pick up the item Basket.
	 *
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void pickUpBasket(ActionEvent event) {
		pickUpBasket();
	}

	/**
	 * Drop the item helmet only if it is stored in Inventory and place it in
	 * specific location that is set. A message is printed according to all
	 * cases.
	 */
	public void dropHelmet() {
		helmetView.setImage(picture.getStoredPictures().get("helmet"));
		// if the item is already dropped
		if (helmet.getSide().equals("Inventory") && helmet.getRoom().equals("Inventory")) {
			// if the item is not dropped
			inventory.showItemsSreen(helmetView, itemHolder1);
			helmet.setSide(homeLocation.getSide());
			helmet.setRoom(homeLocation.getRoom());
			// Message printed when the item is dropped in this side
			System.out.println(
					"Dropped the helmet in " + helmet.getRoom() + " " + helmet.getSide().substring(1) + " side.");

		} else {
			// Message printed when the item is already dropped in another side
			System.out.println(
					"It is already dropped in " + helmet.getRoom() + " " + helmet.getSide().substring(1) + " side.");
			System.out.println("The helmet cannot be dropped in this side.");
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Pick up helmet from a selected side of a room and place it in the
	 * inventory. A message is printed according to all cases.
	 */
	public void pickUpHelmet() {
		itemHolder1.setImage(picture.getStoredPictures().get("helmet"));
		// A message is printed, if the item is already in Inventory
		if (helmet.getSide().equals("Inventory") && helmet.getRoom().equals("Inventory")) {
			System.out.println("The helmet is already in inventory.");
			System.out.println("It can not be picked up.");

		} else {
			// Message printed when the item is picked up from this side
			if (helmet.getSide().equals(homeLocation.getSide())) {
				inventory.showItemsInventory(helmetView, itemHolder1);
				// Message printed when the item is picked up from this side
				System.out.println("Picked up the helmet from " + homeLocation.getRoom() + " "
						+ helmet.getSide().substring(1) + " side.");
				helmet.setSide("Inventory");
				helmet.setRoom("Inventory");

			} else {
				// Message printed when the item is already dropped in another
				// side
				System.out.println("The helmet is dropped in " + helmet.getRoom() + " " + helmet.getSide().substring(1)
						+ " side.");
				System.out.println("It can not be picked up from this side.");
			}
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Drop guitar only if it is stored in Inventory and place it in specific
	 * location that is set. A message is printed according to all cases.
	 */
	public void dropGuitar() {
		guitarView.setImage(picture.getStoredPictures().get("guitar"));

		if (guitar.getSide().equals("Inventory")) {
			// if the item is not dropped
			inventory.showItemsSreen(guitarView, itemHolder2);
			guitar.setSide(homeLocation.getSide());
			guitar.setRoom(homeLocation.getRoom());
			// Message printed when the item is dropped in this side
			System.out.println(
					"Dropped the guitar in " + guitar.getRoom() + " " + guitar.getSide().substring(1) + " side.");

		} else {
			// Message printed when the item is already dropped in another side
			System.out.println(
					"It is already dropped in " + guitar.getRoom() + " " + guitar.getSide().substring(1) + " side.");
			System.out.println("The tree cannot be dropped in this side.");
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Pick up guitar from a selected side of a room and place it in the
	 * inventory. A message is printed according to all cases.
	 */
	public void pickUpGuitar() {
		itemHolder2.setImage(picture.getStoredPictures().get("guitar"));
		// A message is printed, if the item is already in Inventory
		if (guitar.getSide().equals("Inventory")) {
			System.out.println("The guitar is already in inventory.");
			System.out.println("It can not be picked up.");
		} else {
			// Message printed when the item is picked up from this side
			if (guitar.getSide().equals(homeLocation.getSide())) {
				inventory.showItemsInventory(guitarView, itemHolder2);
				System.out.println("Picked up the guitar from " + homeLocation.getRoom() + " "
						+ guitar.getSide().substring(1) + " side.");
				guitar.setSide("Inventory");
				guitar.setRoom("Inventory");
			} else {
				// Message printed when the item is already dropped in another
				// side
				System.out.println("The guitar is dropped in " + guitar.getRoom() + " " + guitar.getSide().substring(1)
						+ " side.");
				System.out.println("It can not be picked up from this side.");
			}
		}

		System.out.println("-------------------------------------------------");
	}

	/**
	 * Drop tree only if it is stored in Inventory and place it in specific
	 * location that is set. A message is printed according to all cases.
	 */
	public void dropTree() {
		treeView.setImage(picture.getStoredPictures().get("tree"));

		if (tree.getSide().equals("Inventory")) {
			// if the item is not dropped
			inventory.showItemsSreen(treeView, itemHolder);
			tree.setSide(homeLocation.getSide());
			tree.setRoom(homeLocation.getRoom());
			// Message printed when the item is dropped in this side
			System.out.println("Dropped the tree in " + tree.getRoom() + " " + tree.getSide().substring(1) + " side.");

		} else {
			// Message printed when the item is already dropped in another side
			System.out.println(
					"It is already dropped in " + tree.getRoom() + " " + tree.getSide().substring(1) + " side.");
			System.out.println("The tree cannot be dropped in this side.");
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Pick up tree from a selected side of a room and place it in the
	 * inventory. A message is printed according to all cases.
	 */
	public void pickUpTree() {
		itemHolder.setImage(picture.getStoredPictures().get("tree"));
		// A message is printed, if the item is already in Inventory
		if (tree.getSide().equals("Inventory")) {
			System.out.println("The tree is already in inventory.");
			System.out.println("It can not be picked up.");
		} else {
			// Message printed when the item is picked up from this side
			if (tree.getSide().equals(homeLocation.getSide())) {
				inventory.showItemsInventory(treeView, itemHolder);
				System.out.println("Picked up the tree from " + homeLocation.getRoom() + " "
						+ tree.getSide().substring(1) + " side.");
				tree.setSide("Inventory");
				tree.setRoom("Inventory");

			} else {
				// Message printed when the item is already dropped in another
				// side
				System.out.println(
						"The tree is dropped in " + tree.getRoom() + " " + tree.getSide().substring(1) + " side.");
				System.out.println("It can not be picked up from this side.");
			}
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Drop basket only if it is stored in Inventory and place it in specific
	 * location that is set. A message is printed according to all cases.
	 */
	public void dropBasket() {
		basketView.setImage(picture.getStoredPictures().get("basket"));

		if (basket.getSide().equals("Inventory")) {
			// if the item is not dropped
			inventory.showItemsSreen(basketView, itemHolder3);
			basket.setSide(homeLocation.getSide());
			basket.setRoom(homeLocation.getRoom());
			// Message printed when the item is dropped in this side
			System.out.println(
					"Dropped the basket in " + basket.getRoom() + " " + basket.getSide().substring(1) + " side.");

		} else {
			// Message printed when the item is already dropped in another side
			System.out.println(
					"It is already dropped in " + basket.getRoom() + " " + basket.getSide().substring(1) + " side.");
			System.out.println("The basket cannot be dropped in this side.");
		}
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Pick up basket from a selected side of a room and place it in the
	 * inventory. A message is printed according to all cases.
	 */
	public void pickUpBasket() {
		itemHolder3.setImage(picture.getStoredPictures().get("basket"));
		// A message is printed, if the item is already in Inventory
		if (basket.getSide().equals("Inventory")) {
			System.out.println("The basket is already in inventory.");
			System.out.println("It can not be picked up.");
		} else {
			// Message printed when the item is picked up from this side
			if (basket.getSide().equals(homeLocation.getSide())) {
				inventory.showItemsInventory(basketView, itemHolder3);
				System.out.println("Picked up the basket from " + homeLocation.getRoom() + " "
						+ basket.getSide().substring(1) + " side.");
				basket.setSide("Inventory");
				basket.setRoom("Inventory");
			} else {
				// Message printed when the item is already dropped in another
				// side
				System.out.println("The basket is dropped in " + basket.getRoom() + " " + basket.getSide().substring(1)
						+ " side.");
				System.out.println("It can not be picked up from this side.");
			}
		}

		System.out.println("-------------------------------------------------");
	}

	/**
	 * Set buttons visible or invisible, depending the location.
	 */
	@Override
	public void topArrowVisibility() {
		imageView.requestFocus();
		if (homeLocation.getSide().equals("hLeft") || homeLocation.getSide().equals("bRight")
				|| homeLocation.getSide().equals("bFront") || homeLocation.getSide().equals("bRight")
				|| homeLocation.getSide().equals("rRight") || homeLocation.getSide().equals("rLeft")
				|| homeLocation.getSide().equals("rFront") || homeLocation.getSide().equals("lFront")
				|| homeLocation.getSide().equals("lRight") || homeLocation.getSide().equals("kRight")
				|| homeLocation.getSide().equals("kLeft") || homeLocation.getSide().equals("kFront")
				|| homeLocation.getSide().equals("hBack")) {
			// hide top arrow when you can no go forward
			buttonArrow.setVisible(false);
			arrowView.setVisible(false);
		} else {
			// show top arrow
			arrowView.setVisible(true);
			buttonArrow.setVisible(true);
			buttonArrow.setBackground(null);
		}
	}

	/**
	 * rotateLeft is used to show the left image of the image shown when
	 * pressing the left button. It also keeps track, whether the items should
	 * be shown or not.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	@Override
	public void rotateLeft(ActionEvent event) {
		homeLocation.setSide(homeLocation.calculateLeftTurn());
		homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());

		topArrowVisibility();
		imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
		checkItemsPosition();
		System.out.println("Rotating left.");
		System.out.println("Currently viewing the " + homeLocation.getSide().substring(1) + " side of the "
				+ homeLocation.getRoom() + ".");
		System.out.println("-------------------------------------------------");

	}

	/**
	 * rotateRight is used to bring the right image when pressing the right
	 * button. It also keeps track, whether the items should be shown or not.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	@Override
	public void rotateRight(ActionEvent event) {

		homeLocation.setSide(homeLocation.calculateRightTurn());
		homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());
		topArrowVisibility();
		imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
		checkItemsPosition();
		System.out.println("Rotating right.");
		System.out.println("Currently viewing the " + homeLocation.getSide().substring(1) + " side of the "
				+ homeLocation.getRoom() + ".");
		System.out.println("-------------------------------------------------");
	}

	/**
	 * randomLocation is used to transfer the user to a random side and room of
	 * the home.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	public void randomLocation(ActionEvent event) {
		imageView.requestFocus();
		homeLocation.randomRoom();
		imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
		checkItemsPosition();
		System.out.println("Location was chosen at random");
		System.out.println("New location is: " + homeLocation.getRoom() + " room and "
				+ homeLocation.getSide().substring(1) + " side");
		System.out.println("-------------------------------------------------");

	}

	/**
	 * Set buttons background transparent and set images.
	 */
	@Override
	public void initializeImages() {
		// get images from HashMap and set
		posView.setImage(picture.getStoredPictures().get("posArrow"));
		mapView.setImage(picture.getStoredPictures().get("map"));
		imageView.setImage(picture.getStoredPictures().get("hFront"));
		itemHolder.setImage(picture.getStoredPictures().get("tree"));
		itemHolder1.setImage(picture.getStoredPictures().get("helmet"));
		itemHolder2.setImage(picture.getStoredPictures().get("guitar"));
		itemHolder3.setImage(picture.getStoredPictures().get("basket"));
		guitarView.setImage(picture.getStoredPictures().get("guitar"));
		basketView.setImage(picture.getStoredPictures().get("basket"));
		treeView.setImage(picture.getStoredPictures().get("tree"));
		helmetView.setImage(picture.getStoredPictures().get("helmet"));
		exitView.setImage(picture.getStoredPictures().get("exit"));
		randomView.setImage(picture.getStoredPictures().get("random"));
		arrowView.setImage(picture.getStoredPictures().get("tArrow"));
		buttonArrow.setBackground(null);
		leftArrowView.setImage(picture.getStoredPictures().get("lArrow"));
		buttonLeft.setBackground(null);
		rightArrowView.setImage(picture.getStoredPictures().get("rArrow"));
		buttonRight.setBackground(null);
		exitView.setImage(picture.getStoredPictures().get("exit"));
		buttonExit.setBackground(null);
		randomButton.setBackground(null);

	}

	/**
	 * Show or hide items depending on their position.
	 */
	@Override
	public void checkItemsPosition() {

		// check if the location of the tree is the currently image shown
		if (homeLocation.getRoom().equals(tree.getRoom()) && homeLocation.getSide().equals(tree.getSide())) {
			inventory.showItemsSreen(treeView, itemHolder);
			// check if item is in inventory
		} else if (tree.getSide().equals("Inventory")) {
			inventory.showItemsInventory(treeView, itemHolder);
			// check if the item is already dropped in another side
		} else {
			inventory.hideItems(treeView, itemHolder);
		}

		// check if the location of the helmet is the currently image shown
		if (homeLocation.getRoom().equals(helmet.getRoom()) && homeLocation.getSide().equals(helmet.getSide())) {
			inventory.showItemsSreen(helmetView, itemHolder1);
			// check if item is in inventory
		} else if (helmet.getSide().equals("Inventory")) {
			inventory.showItemsInventory(helmetView, itemHolder1);
			// check if the item is already dropped in another side
		} else {
			inventory.hideItems(helmetView, itemHolder1);
		}

		// check if the location of the guitar is the currently image shown
		if (homeLocation.getRoom().equals(guitar.getRoom()) && homeLocation.getSide().equals(guitar.getSide())) {
			inventory.showItemsSreen(guitarView, itemHolder2);
			// check if item is in inventory
		} else if (guitar.getSide().equals("Inventory")) {
			inventory.showItemsInventory(guitarView, itemHolder2);
			// check if the item is already dropped in another side
		} else {
			inventory.hideItems(guitarView, itemHolder2);
		}

		// check if the location of the basket is the currently image shown
		if (homeLocation.getRoom().equals(basket.getRoom()) && homeLocation.getSide().equals(basket.getSide())) {
			inventory.showItemsSreen(basketView, itemHolder3);
			// check if item is in inventory
		} else if (basket.getSide().equals("Inventory")) {
			inventory.showItemsInventory(basketView, itemHolder3);
			// check if the item is already dropped in another side
		} else {
			inventory.hideItems(basketView, itemHolder3);
		}
		imageView.requestFocus();
	}

	/**
	 * moveWithButtons is used to rotate or go forward with buttons (A,W,D),
	 * while clicking the arrows on the screen.
	 * 
	 */
	@Override
	public void moveWithButtons() {
		imageView.setFocusTraversable(true);
		imageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// move Left
				if (event.getCode() == KeyCode.A) {
					homeLocation.setSide(homeLocation.calculateLeftTurn());
					homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());

					topArrowVisibility();
					imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
					checkItemsPosition();
					System.out.println("Rotating left.");
					System.out.println("Currently viewing the " + homeLocation.getSide().substring(1) + " side of the "
							+ homeLocation.getRoom() + ".");
					System.out.println("-------------------------------------------------");

					// move Right
				} else if (event.getCode() == KeyCode.D) {
					homeLocation.setSide(homeLocation.calculateRightTurn());
					homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());

					topArrowVisibility();
					imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
					checkItemsPosition();
					System.out.println("Rotating right.");
					System.out.println("Currently viewing the " + homeLocation.getSide().substring(1) + " side of the "
							+ homeLocation.getRoom() + ".");
					System.out.println("-------------------------------------------------");

					// go Forward
				} else if (event.getCode() == KeyCode.W) {
					if (homeLocation.getSide().equals("hFront") || homeLocation.getSide().equals("hRight")
							|| homeLocation.getSide().equals("bLeft") || homeLocation.getSide().equals("bBack")
							|| homeLocation.getSide().equals("rBack") || homeLocation.getSide().equals("mFront")
							|| homeLocation.getSide().equals("mRight") || homeLocation.getSide().equals("lBack")
							|| homeLocation.getSide().equals("lLeft") || homeLocation.getSide().equals("kBack")
							|| homeLocation.getSide().equals("mLeft") || homeLocation.getSide().equals("mBack")) {
						homeLocation.goForward();

						homeLocation.updateLocation(homeLocation.getRoom(), homeLocation.getSide());
						topArrowVisibility();

						imageView.setImage(picture.getStoredPictures().get(homeLocation.getSide()));
						homeLocation.changeMapPointer(posView);
						checkItemsPosition();
						System.out.println("Moving forward to the " + homeLocation.getRoom());
						System.out.println("-------------------------------------------------");
					}

				}
			}
		}

		);
	}

	/**
	 * Can drop or pick up an item by clicking on the according ImageView.
	 */
	public void clickItems() {
		basketView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				pickUpBasket();
				event.consume();
			}
		});
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				pickUpTree();
				event.consume();
			}
		});
		guitarView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				pickUpGuitar();
				event.consume();
			}
		});
		helmetView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				pickUpHelmet();
				event.consume();
			}
		});
		itemHolder3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dropBasket();
				event.consume();
			}
		});
		itemHolder2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dropGuitar();
				event.consume();
			}
		});
		itemHolder1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dropHelmet();
				event.consume();
			}
		});
		itemHolder.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dropTree();
				event.consume();
			}
		});
	}

	/**
	 * consoleOutput is using the redirectText to write the console output with
	 * OutputStream.
	 */
	public void consoleOutput() {
		OutputStream out = new OutputStream() {
			@Override
			public void write(int b) {
				redirectText(String.valueOf((char) b));
			}
		};
		System.setOut(new PrintStream(out, true));
		output.setWrapText(true);
	}

	/**
	 * redirectText shows the console output in text area.
	 * 
	 * @param text
	 *            text is the String written in console.
	 */
	public void redirectText(String text) {
		Platform.runLater(() -> output.appendText(text));
	}

	/**
	 * Exit platform.
	 */
	@Override
	public void exit() {
		Platform.exit();
	}

}
