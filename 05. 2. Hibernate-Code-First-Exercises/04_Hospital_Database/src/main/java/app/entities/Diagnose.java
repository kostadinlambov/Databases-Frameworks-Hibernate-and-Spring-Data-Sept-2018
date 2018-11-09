package app.entities;

import com.mysql.cj.xdevapi.CreateViewStatement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {
    private String name;
    private String comment;
    private Visitation visitation;

    public Diagnose() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "comment", nullable = false)
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToOne(mappedBy = "diagnose", targetEntity = Visitation.class)
    public Visitation getVisitation() {
        return this.visitation;
    }

    public void setVisitation(Visitation visitation) {
        this.visitation = visitation;
    }
}
