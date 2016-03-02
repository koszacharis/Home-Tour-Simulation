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
package ijp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Properties class contains methods to retrieve String values from property
 * file, such as room, sides, path and the starting position of the application
 * and items.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */

public class Properties {

	private static java.util.Properties properties;
	private static final String propFile = "default.properties";

	// hardcoded values of rooms and sides size
	private static final int roomsNumber = 6;
	private static final int sidesNumber = 4;

	private HashMap<Integer, String> roomFromProperties = new HashMap<Integer, String>();
	private HashMap<Integer, String> sideFromProperties = new HashMap<Integer, String>();

	/**
	 * Load sides and rooms from default.properties.
	 */
	public Properties() {
		loadRoomsFromProperties();
		loadSidesFromProperties();
	}

	/**
	 * Return the position of an item when requested.
	 *
	 * @param itemPosition
	 *            the position of the item
	 * @return the item's position
	 */
	public String load(String itemPosition) {
		properties = new java.util.Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile);
		try {
			properties.load(inputStream);
			itemPosition = properties.getProperty(itemPosition);
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return itemPosition;
	}

	/**
	 * Read rooms from default.properties file.
	 */
	public void loadRoomsFromProperties() {
		for (int i = 1; i < roomsNumber + 1; i++) {
			roomFromProperties.put(i, load("Room".trim() + i));
		}

	}

	/**
	 * Read sides from default.properties file.
	 */
	public void loadSidesFromProperties() {
		for (int i = 1; i < sidesNumber + 1; i++) {
			sideFromProperties.put(i, load("Side" + i));
		}
	}

	/**
	 * Read the path from default.properties
	 * 
	 * @return the path from default.properties
	 */
	public String readPath() {
		return load("ResourcesPath");
	}

	/**
	 * Get the room that is saved in HashMap.
	 * 
	 * @return the specified value of the HashMap
	 */
	public HashMap<Integer, String> getRoomFromProperties() {
		return roomFromProperties;
	}

	/**
	 * Set a new entry to the HashMap.
	 * 
	 * @param roomFromProperties
	 *            roomFromProperties is the HashMap that room names are stored.
	 */
	public void setRoomFromProperties(HashMap<Integer, String> roomFromProperties) {
		this.roomFromProperties = roomFromProperties;
	}

	/**
	 * Get the side that is saved in HashMap.
	 * 
	 * @return the specified value of the HashMap
	 */
	public HashMap<Integer, String> getSideFromProperties() {
		return sideFromProperties;
	}

	/**
	 * Set a new entry to the HashMap.
	 * 
	 * @param sideFromProperties
	 *            sideFromProperties is the HashMap that side names are stored.
	 */
	public void setSideFromProperties(HashMap<Integer, String> sideFromProperties) {
		this.sideFromProperties = sideFromProperties;
	}

}
