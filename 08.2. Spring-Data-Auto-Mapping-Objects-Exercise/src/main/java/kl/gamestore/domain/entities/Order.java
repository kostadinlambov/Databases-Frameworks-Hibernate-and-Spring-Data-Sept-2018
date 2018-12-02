package kl.gamestore.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User user;
    private Game game;

    public Order() {
    }

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Game.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id")
    public Game getGame() {
        return this.game;
    }

    public void setGame(Game games) {
        this.game = games;
    }

}
