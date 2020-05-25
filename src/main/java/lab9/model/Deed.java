package lab9.model;

import javax.persistence.*;

@Entity
@Table(name="deeds")
public class Deed {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;

    public Deed(){}

    public Deed(Integer id_, String name_, String status_)
    {
        this.id = id_;
        this.name = name_;
        this.status = status_;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
