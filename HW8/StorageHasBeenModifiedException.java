package HW8;

import java.io.Serializable;

public class StorageHasBeenModifiedException extends Exception implements Serializable {

    private static final long serialVersionUID = 7861263817263L;


    public StorageHasBeenModifiedException(String errorMessage) {
        super(errorMessage);
    }

    StorageHasBeenModifiedException() {

    }
}
