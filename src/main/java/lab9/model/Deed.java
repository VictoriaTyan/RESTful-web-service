package lab9.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="deeds")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Deed {
    @Id
    @SequenceGenerator(name = "idSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;

    public Deed(){}

    public Deed(String name_, String status_)
    {
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
