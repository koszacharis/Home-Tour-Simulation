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
package ijp.picture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Class for retrieving images from resources folder and storing them in a
 * HashMap.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 *
 */
public class Picture {

	private HashMap<String, Image> storedPictures = new HashMap<String, Image>();

	private int imgCounter;

	private static final String url = "https://www.dropbox.com/sh/jk76x9g8tczl633/AABF0ViVS8oxVxpubc2Ui2eVa?dl=0";
	private static final String filename = "DownloadImages";

	// the path of the directories (e.g. /Users/.../IJPAssignment2) that
	// contain IJPAssignment2.
	private static final String directoryPath = System.getProperty("user.dir");

	// the inner path of the folder resources
	private static final String resourcesPath = "/resources";

	/**
	 * Add new image to HashMap.
	 * 
	 * @param imageName
	 *            image name of the new image
	 *            e.g.(#IMAGE_NAME_WITHOUT_EXTENSION#)
	 * @param path
	 *            the path of the new image e.g.
	 *            (#file:resources/IMAGE_NAME_EXTENSION#)
	 */
	public void addPicture(String imageName, Image path) {
		if (path.equals(storedPictures.get(imageName))) {
			System.out.println("The image already exists.");
		} else {
			storedPictures.put(imageName, path);
		}
	}

	/**
	 * If resources folder is empty automatically downloads a public link
	 * provided from Dropbox. After the folder is created you need to click on
	 * the folder that will redirect you in the online public folder in Dropbox
	 * and download them from there in the resources folder.
	 */
	public void downloadPictures() {

		try {
			URL link = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(link.openStream());
			FileOutputStream fos = new FileOutputStream(filename);
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			fos.close();
			rbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enables to load the images into the jar and making it executable from any
	 * directory without having the resources folder.
	 */
	public void jarPictureLoader() {
		CodeSource cs = Picture.class.getProtectionDomain().getCodeSource();
		try {
			if (cs != null) {
				System.out.println(cs);
				ZipEntry zipEntry = null;
				URL jarFile = cs.getLocation();
				ZipInputStream zip = new ZipInputStream(jarFile.openStream());

				while ((zipEntry = zip.getNextEntry()) != null) {
					String path = zipEntry.getName();
					if (path.endsWith(".png")) {
						storedPictures.put(path.substring(0, path.lastIndexOf(".")), new Image(path));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Store image names and Images to HashMap, through iterating the folder
	 * "/resources", where images are originally stored.
	 * 
	 * @throws IOException
	 *             throw IOException
	 */
	public void pictureReader() throws IOException {
		imgCounter = 0;
		// change to newFile(readPath())
		File dir = new File(directoryPath + resourcesPath);
		File[] resourcesList = dir.listFiles();
		if (dir.isDirectory() && dir.exists()) {
			if (resourcesList != null) {
				System.out.println("Inserting images to HashMap!");
				System.out.println("...");
				System.out.println("...");
				System.out.println("...");
				for (File path : resourcesList) {
					// insert the name of the picture as key
					// and as value the Image path
					storedPictures.put(path.getPath().substring(directoryPath.length() + resourcesPath.length() + 1,
							path.getPath().lastIndexOf(".")), new Image("file:resources/" + path.getName()));
					imgCounter++;
				}
				System.out.println("Total images inserted: " + imgCounter);
				System.out.println("All images loaded successfully!");
				System.out.println("Application started.");
				System.out.println("-------------------------------------------------");

			} else {
				System.out.println("[error]The resources folder is empty.");
				System.out.println("The images are downloaded from the public folder link provided");
				downloadPictures();
				System.out.println("The public folder is located inside the project path!");
				System.out.println("The folder is downloaded successfully");
			}

		} else {

			System.out.println("[error]The resource folder does not exist.");
			System.out.println("Please change the path from default.properties file");
			Platform.exit();
		}
	}

	/**
	 * Return the HashMap storedPictures.
	 * 
	 * @return the HashMap storedPictures.
	 */
	public HashMap<String, Image> getStoredPictures() {
		return storedPictures;
	}
}