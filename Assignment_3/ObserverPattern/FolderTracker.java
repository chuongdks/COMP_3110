import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * 
 */
@SuppressWarnings("deprecation")
public class FolderTracker extends Observable implements Runnable {
    private final File folder;                              // New File instance (recommend to use folder)
    private Map<String, Long> currentFiles;                 // List of current Files that is in the directory

    public FolderTracker(String folderPath) {
        this.folder = new File(folderPath);

        // Checker to see if File provided is a directory
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The path provided is not a directory.");
        }
        this.currentFiles = scanDirectory();                // Populate the Maps with current files in the directory
    }

    @Override
    public void run() {
        while (true) {
            Map<String, Long> newFiles = scanDirectory();   // keep scanning and creating new files in the Directory while the program is running
            detectChanges(newFiles);                        // Check for any changes in the folder directory
            currentFiles = newFiles;

            // How to sleep in Java: https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Scan the Folder directory by creating a new filesMap every 1s
     * @return
     */
    private Map<String, Long> scanDirectory() {
        Map<String, Long> filesMap = new HashMap<>();   // 
        File[] files = folder.listFiles();              //

        // Keep scanning and adding new files
        if (files != null) 
        {
            for (File file : files) 
            {
                filesMap.put(file.getName(), file.lastModified()); // Use the File Name as the Key and Last Modified data as the value
            }
        }
        return filesMap;
    }

    /**
     * 
     * @param newFiles
     */
    private void detectChanges(Map<String, Long> newFiles) 
    {
        // Check for added or modified files based on the newFiles List
        for (String fileName : newFiles.keySet()) 
        {
            if (!currentFiles.containsKey(fileName))                                // if the current Files List does not have this new file name then notify
            {
                notifyChange("Added: " + fileName);
            } 
            else if (!currentFiles.get(fileName).equals(newFiles.get(fileName)))    // get each file lastModified data and compare them with the new Files List
            {
                notifyChange("Modified: " + fileName);
            }
        }

        // Check for deleted files by checking all the files exist in currentFiles compare to newFiles
        for (String fileName : currentFiles.keySet()) 
        {
            if (!newFiles.containsKey(fileName))                                    // If newFiles list does not contain the file in currentFiles, it got deleted 
            {
                notifyChange("Deleted: " + fileName);
            }
        }
    }

    /**
     * 
     * @param message
     */
    private void notifyChange(String message) {
        setChanged();
        notifyObservers(message);
    }
}