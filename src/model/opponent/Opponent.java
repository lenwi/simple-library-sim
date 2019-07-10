package model.opponent;

public class Opponent {

    private String name;
    private CharStats stats;
    private Selection sel;
    private Breaker breaker;

    public Opponent (String name, CharStats stats, Selection sel, Breaker breaker) {
        this.name = name;
        this.stats = stats;
        this.sel = sel;
        this.breaker = breaker;
    }

    // getters
    public String getName() {return name;}
    public CharStats getStat() {return stats;}
    public Selection getSel() {return sel;}
    public Breaker getBreaker() {return breaker;}

    // MODIFIES: this
    // EFFECTS: sets this opponent's name to given name
    public void setName (String name) {
        this.name =name;
    }

    // MODIFIES: this
    // EFFECTS: returns this opponent's character stats
    public void setOpponentStats (CharStats stats) {
        this.stats = stats;
    }

    // MODIFIES: this
    // EFFECTS: sets the opponent's selection to one of "rock", "paper", or "scissors"
    public void setSelection (Selection sel) {
        this.sel = sel;
    }

    // MODIFIES: this
    // EFFECTS: returns this opponent's breaker streak
    public void setBreaker (Breaker breaker) {
        this.breaker = breaker;
    }
}
