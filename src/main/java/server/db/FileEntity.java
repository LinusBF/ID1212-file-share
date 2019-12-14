package server.db;
import interfaces.FileDTO;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "owner")
    private String owner;

    @Column(name = "size")
    private int size;

    public FileEntity(FileDTO file){
        this.filename = file.getName();
        this.owner = file.getOwner();
        this.size = file.getSize();
    }

    public FileEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public static FileDTO getFileDTO(FileEntity f){
        return new FileDTO(f.filename, f.owner, f.size);
    }
}
