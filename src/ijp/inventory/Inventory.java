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

import javafx.scene.image.ImageView;

/**
 * Inventory class contains methods involved with the location and visibility of
 * the items. It also uses load method from Property class to load the starting
 * positions of each item and place them accordingly.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @version 1.0.4, 27 Nov 2015
 */

public class Inventory {

	/**
	 * Set the imageView of the item in the Inventory location visible and hide
	 * the the imageView of the item in the screen.
	 * 
	 * 
	 * @param imageView
	 *            the ImageView used to show the items on the screen.
	 * @param inventoryView
	 *            the ImageView used to show the items on the inventory.
	 */
	public void showItemsInventory(ImageView imageView, ImageView inventoryView) {
		imageView.setVisible(false);
		inventoryView.setVisible(true);
	}

	/**
	 * Set the imageView of the item in the screen visible and hide the
	 * imageView of the item in the Inventory location.
	 * 
	 * @param imageView
	 *            the ImageView used to show the items on the screen.
	 * @param inventoryView
	 *            the ImageView used to show the items on the inventory.
	 */
	public void showItemsSreen(ImageView imageView, ImageView inventoryView) {
		imageView.setVisible(true);
		inventoryView.setVisible(false);
	}

	/**
	 * Set both locations of the imageViews false.
	 * 
	 * @param imageView
	 *            the ImageView used to hide the items on the screen.
	 * @param inventoryView
	 *            the ImageView used to hide the items on the inventory.
	 */
	public void hideItems(ImageView imageView, ImageView inventoryView) {
		imageView.setVisible(false);
		inventoryView.setVisible(false);
	}

}
