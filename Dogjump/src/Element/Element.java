package Element;

import java.awt.Font;
import java.io.File;

public class Element {
    public Element() {
    }

    public static Font getFont(int size) {
        Font font = null;

        try {
            font = Font.createFont(0, new File("font\\Mali-Bold.ttf"));
            return font.deriveFont((float)size);
        } catch (Exception var3) {
            var3.printStackTrace();
            return font;
        }
    }
}