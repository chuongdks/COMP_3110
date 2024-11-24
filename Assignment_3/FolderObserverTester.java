/**
 * 
 */
public class FolderObserverTester {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String directoryPath = "D:\\uWindsor\\SEMESTER_4\\COMP-3110\\Assignment\\Assignment 3";
    
        FolderTracker watcher = new FolderTracker(directoryPath);
    
        FolderObserver observer = new FolderObserver();
        watcher.addObserver(observer);
    
        Thread watcherThread = new Thread(watcher);
        watcherThread.start();
    
        System.out.println("Watching directory: " + directoryPath);
    }
}
