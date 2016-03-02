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
package ijp.inventory;

import ijp.utils.Properties;

/**
 * Item class is used to create an item of the specified name.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */
public class Item {

	private String side;
	private String room;
	private Properties properties = new Properties();

	/**
	 * Initialize the starting position (side, room) of an object type item.
	 * 
	 * @param side
	 *            the side of an item in a room
	 * @param room
	 *            the room position of an item
	 */
	public Item(String side, String room) {
		this.side = properties.load(side);
		this.room = properties.load(room);
	}

	/**
	 * Returns the position (side) of the item.
	 * 
	 * @return the side of the item
	 */
	public String getSide() {
		return side;
	}

	/**
	 * Set the position (side) of the item.
	 * 
	 * @param side
	 *            the side where the item is.
	 */
	public void setSide(String side) {
		this.side = side;
	}

	/**
	 * Returns the position (room) of the item.
	 * 
	 * @return the room of the item.
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Set the position (room) of the item.
	 * 
	 * @param room
	 *            the room where the item is.
	 */
	public void setRoom(String room) {
		this.room = room;
	}
}
