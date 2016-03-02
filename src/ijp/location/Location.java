package ijp.location;

import javafx.scene.image.ImageView;

public interface Location {

	/**
	 * Update the location with new values of room and side.
	 * 
	 * @param roomLocation
	 *            roomLocation is used to store the name of the room
	 * @param sideLocation
	 *            sideLocation is used to store the name of the side
	 */
	void updateLocation(String roomLocation, String sideLocation);

	/**
	 * changePosition is used to change the position arrow with specific
	 * location properties depending on where is the room on the map.
	 * 
	 * @param posView
	 *            the ImageView of the pointer.
	 */
	void changeMapPointer(ImageView posView);

	/**
	 * Sets the next side to be shown when turned right.
	 * 
	 * @return Return the first letter of the Room with the selected side.
	 */
	String calculateRightTurn();

	/**
	 * Sets the next side to be shown when turned left.
	 * 
	 * @return Return the first letter of the Room with the selected Side
	 */
	String calculateLeftTurn();

	/**
	 * Sets the next side and room to be shown when going forward.
	 */
	void goForward();

	/**
	 * randomRoom uses Random class to choose the next destination(room) at
	 * random.
	 * 
	 */
	void randomRoom();

}