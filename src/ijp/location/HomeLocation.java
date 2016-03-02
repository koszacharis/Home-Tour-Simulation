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
package ijp.location;

import java.util.Random;

import ijp.utils.Properties;
import javafx.scene.image.ImageView;

/**
 * HomeLocation class implements Location interface and contains the
 * implementation of methods that show, which location is the current image
 * shown, depending on the image. It also contains methods to turn right or left
 * and go forward to the next room, while updating the values of room and side
 * of the image shown.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */

public class HomeLocation implements Location {

	private static Random random = new Random();
	private Properties properties = new Properties();

	// // initialize the starting point of the application.
	private String room = properties.getRoomFromProperties().get(1);
	private String side = room.substring(0, 1).toLowerCase() + properties.getSideFromProperties().get(1);

	/**
	 * Update the location with new values of room and side.
	 * 
	 * @param roomLocation
	 *            roomLocation is used to store the name of the room
	 * @param sideLocation
	 *            sideLocation is used to store the name of the side
	 */
	@Override
	public void updateLocation(String roomLocation, String sideLocation) {
		this.room = roomLocation;
		this.side = sideLocation;
	}

	/**
	 * changePosition is used to change the position arrow with specific
	 * location properties depending on where is the room on the map.
	 * 
	 * @param posView
	 *            the ImageView of the pointer.
	 */
	@Override
	public void changeMapPointer(ImageView posView) {
		if (room.equals(properties.getRoomFromProperties().get(1))) {
			posView.relocate(789, 272);

		} else if (room.equals(properties.getRoomFromProperties().get(2))) {
			posView.relocate(788, 185);

		} else if (room.equals(properties.getRoomFromProperties().get(3))) {
			posView.relocate(880.0, 281);

		} else if (room.equals(properties.getRoomFromProperties().get(4))) {
			posView.relocate(867.0, 200);

		} else if (room.equals(properties.getRoomFromProperties().get(5))) {
			posView.relocate(880.0, 64.0);

		} else {
			posView.relocate(788.0, 60.0);
		}
	}

	/**
	 * Sets the next side to be shown when turned right.
	 * 
	 * @return Return the first letter of the Room with the selected side.
	 */
	@Override
	public String calculateRightTurn() {
		// check the current side with the side of the retrieved value from
		// properties file.
		if (side.substring(1).equals(properties.getSideFromProperties().get(1))) {
			this.side = properties.getSideFromProperties().get(2);

		} else if (side.substring(1).equals(properties.getSideFromProperties().get(2))) {
			this.side = properties.getSideFromProperties().get(3);

		} else if (side.substring(1).equals(properties.getSideFromProperties().get(3))) {
			this.side = properties.getSideFromProperties().get(4);

		} else {
			this.side = properties.getSideFromProperties().get(1);
		}
		// return the new side to update the image shown with the starting
		// letter of the room and the side to be the same as the name of the
		// image.
		return this.room.substring(0, 1).toLowerCase() + this.side;
	}

	/**
	 * Sets the next side to be shown when turned left.
	 * 
	 * @return Return the first letter of the Room with the selected Side
	 */
	@Override
	public String calculateLeftTurn() {
		// check the current side with the side of the retrieved value from
		// properties file.
		if (side.substring(1).equals(properties.getSideFromProperties().get(1))) {
			this.side = properties.getSideFromProperties().get(4);

		} else if (side.substring(1).equals(properties.getSideFromProperties().get(2))) {
			this.side = properties.getSideFromProperties().get(1);

		} else if (side.substring(1).equals(properties.getSideFromProperties().get(3))) {
			this.side = properties.getSideFromProperties().get(2);

		} else {
			this.side = properties.getSideFromProperties().get(3);

		}
		// return the new side to update the image shown with the starting
		// letter of the room and the side to be the same as the name of the
		// image.
		return this.room.substring(0, 1).toLowerCase() + this.side;
	}

	/**
	 * Sets the next side and room to be shown when going forward.
	 */
	@Override
	public void goForward() {
		// check side and move the according room and side
		if (side.equals("hRight")) {
			this.side = "bFront";
			this.room = properties.getRoomFromProperties().get(3);

		} else if (side.equals("hFront")) {
			this.side = "mFront";
			this.room = properties.getRoomFromProperties().get(2);

		} else if (side.equals("bLeft")) {
			this.side = "rLeft";
			this.room = properties.getRoomFromProperties().get(4);

		} else if (side.equals("rBack")) {
			this.side = "bBack";
			this.room = properties.getRoomFromProperties().get(3);

		} else if (side.equals("bBack")) {
			this.side = "hLeft";
			this.room = properties.getRoomFromProperties().get(1);

		} else if (side.equals("kBack")) {
			this.side = "lFront";
			this.room = properties.getRoomFromProperties().get(5);

		} else if (side.equals("lLeft")) {
			this.side = "kRight";
			this.room = properties.getRoomFromProperties().get(6);

		} else if (side.equals("lBack")) {
			this.side = "mBack";
			this.room = properties.getRoomFromProperties().get(2);

		} else if (side.equals("mFront")) {
			this.side = "lFront";
			this.room = properties.getRoomFromProperties().get(5);

		} else if (side.equals("mBack")) {
			this.side = "hBack";
			this.room = properties.getRoomFromProperties().get(1);

		} else {
			randomRoom();
			System.out.println("Entered Wormhole!");
			System.out.println("Location was chosen at random");
			System.out.println("New location is: " + room + " room and " + side.substring(1) + " side");

		}
	}

	/**
	 * randomRoom uses Random class to choose the next destination(room) at
	 * random.
	 * 
	 */
	@Override
	public void randomRoom() {
		int randSide = random.nextInt(3) + 1;
		String side = properties.getSideFromProperties().get(randSide);
		int randRoom = random.nextInt(6) + 1;
		this.room = properties.getRoomFromProperties().get(randRoom);
		this.side = this.room.substring(0, 1).toLowerCase() + side;
	}

	/**
	 * Return a value for the side.
	 * 
	 * @return a value for the side.
	 */
	public String getSide() {
		return side;
	}

	/**
	 * Set value for the side.
	 * 
	 * @param side
	 *            the value of the side.
	 */
	public void setSide(String side) {
		this.side = side;
	}

	/**
	 * Return a value for the room.
	 * 
	 * @return a value for the room.
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Set value for the room.
	 * 
	 * @param room
	 *            the value of the room.
	 */
	public void setRoom(String room) {
		this.room = room;
	}
}
