package com.github.fbdo.pricing;

import java.io.File;

/**
 * Created by fabio on 28/09/14.
 */
public final class FileUtils {

    private FileUtils() {}

    public static File getScriptFolder(String exampleName) {
        File folder = new File("src/main/resources").getAbsoluteFile();
        File exampleFolder = new File(folder,
                exampleName);
        if (!exampleFolder.exists()) {
            throw new RuntimeException("The target folder does not exist, please create folder " + exampleFolder + " first");
        }

        return exampleFolder;
    }
}
