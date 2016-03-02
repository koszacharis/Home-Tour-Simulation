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

import javafx.event.ActionEvent;

/**
 * A controller for the HomeViewer application.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 *
 */
public interface Controller {

	/**
	 * moveWithButtons is used to rotate or go forward with buttons (A,W,D),
	 * while clicking the arrows on the screen.
	 * 
	 */
	void moveWithButtons();

	/**
	 * goForward is triggered when the top arrow is pressed. It is used to
	 * change room depending the location, when pressed.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	void goForward(ActionEvent event);

	/**
	 * Set buttons visible or invisible, depending the location.
	 */
	void topArrowVisibility();

	/**
	 * rotateLeft is used to show the left image of the image shown when
	 * pressing the left button. It also keeps track, whether the items should
	 * be shown or not.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	void rotateLeft(ActionEvent event);

	/**
	 * rotateRight is used to bring the right image when pressing the right
	 * button. It also keeps track, whether the items should be shown or not.
	 * 
	 * @param event
	 *            The event is used to represent the action.
	 */
	void rotateRight(ActionEvent event);

	/**
	 * Set buttons background transparent and set images.
	 */
	void initializeImages();

	/**
	 * Exit platform.
	 */
	void exit();

	/**
	 * Show or hide items depending on their position.
	 */
	void checkItemsPosition();

}