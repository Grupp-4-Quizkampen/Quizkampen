package Server;

import java.io.Serializable;

public class PlayerData implements Serializable {
    private String name;
    private int avatar;

    public PlayerData(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }
}
