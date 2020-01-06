package interfaces;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDTO fileDTO = (FileDTO) o;
        return size == fileDTO.size &&
                name.equals(fileDTO.name) &&
                owner.equals(fileDTO.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, size);
    }
}
