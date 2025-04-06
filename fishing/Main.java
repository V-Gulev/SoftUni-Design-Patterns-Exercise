package fishing;

import fishing.core.Controller;
import fishing.core.ControllerImpl;
import fishing.core.Engine;
import fishing.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}