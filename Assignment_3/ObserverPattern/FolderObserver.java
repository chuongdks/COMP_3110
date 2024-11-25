import java.util.Observable;
import java.util.Observer;

/**
 * 
 */
@SuppressWarnings("deprecation")
class FolderObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Change detected: " + arg);
    }
}