package app.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity{
    private LocalDate date;
    private String comment;
    private Diagnose diagnose;
    private Set<Medicament> medicaments;
    private Patient patient;

    public Visitation() {
    }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "comment", nullable = false)
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToOne(targetEntity = Diagnose.class, optional = false)
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    public Diagnose getDiagnose() {
        return this.diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @ManyToMany(targetEntity = Medicament.class)
    @JoinTable(name = "visitations_medicaments",
    joinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medicamment_id", referencedColumnName = "id"))
    public Set<Medicament> getMedicaments() {
        return this.medicaments;
    }

    public void setMedicaments(Set<Medicament> medikaments) {
        this.medicaments = medicaments;
    }

    @ManyToOne(targetEntity = Patient.class, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
