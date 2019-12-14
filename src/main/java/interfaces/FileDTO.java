package interfaces;

import java.io.Serializable;

public class FileDTO implements Serializable {
    private String name;
    private String owner;
    private int size;

    public FileDTO(String name, String owner, int size) {
        this.name = name;
        this.owner = owner;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File: " + name + " (" + size + ") owner: " + owner;
    }
}
