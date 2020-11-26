package Client;

import javax.swing.*;
import java.lang.reflect.Array;

public class AvatarDatabase {
    ImageIcon[] avatars = {
            new ImageIcon("src/Client/AvatarPictures/avatar1.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar2.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar3.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar4.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar5.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar6.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar7.png"),
            new ImageIcon("src/Client/AvatarPictures/avatar8.png")
    };
    public static ImageIcon[] getAvatars() {
        return new ImageIcon[]{
                new ImageIcon("src/Client/AvatarPictures/avatar1.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar2.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar3.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar4.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar5.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar6.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar7.png"),
                new ImageIcon("src/Client/AvatarPictures/avatar8.png")
        };
    }
}

