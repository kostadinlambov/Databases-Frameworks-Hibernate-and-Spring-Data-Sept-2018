package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "races")
public class Race extends BaseEntity {

    private Integer laps;
    private District district;
    private List<RaceEntry> raceEntries;

    public Race() {
    }

    @Column(name = "laps", nullable = false, columnDefinition = "INT(11) default 0")
    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(
            name = "district_id",
            referencedColumnName = "id",
            nullable = false
    )
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(targetEntity = RaceEntry.class, mappedBy = "race")
    public List<RaceEntry> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
