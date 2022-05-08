package assignment1.BusinessLayer.Service;

import assignment1.BusinessLayer.BusinessLogicException;

public class ServiceResponse {
    public final boolean success;
    public final String error;

    ServiceResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    void throwIfErroneous() throws BusinessLogicException {
        if(!success) {
            throw new BusinessLogicException(error);
        }
    }

    void throwRuntimeIfErroneous() {
        if(!success) {
            throw new RuntimeException(new BusinessLogicException(error));
        }
    }
}