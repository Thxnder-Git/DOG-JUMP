package event;

import Charactor.Dog;
import Charactor.Environment;
import Charactor.Wave;

public class Event {
    public Event() {
    }

    public static boolean checkHit(Dog dog, Wave wave, int dogSize, int waveHeight) {
        return dog.x + dogSize > wave.x && dog.x < wave.x && dog.y + dogSize >= wave.y - waveHeight;
    }

    public static void gameStop(Wave[] wave, Environment[] env) {
    }
}