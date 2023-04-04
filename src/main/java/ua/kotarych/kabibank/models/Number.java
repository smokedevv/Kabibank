package ua.kotarych.kabibank.models;

public class Number {

    public Number(int atm, int card){
        this.atm = atm;
        this.card = card;
    }

    private int atm;
    private int card;

    public int getAtm() {
        return atm;
    }

    public int getCard() {
        return card;
    }

    public void setAtm(int atm) {
        this.atm = atm;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public void addAtm(){
        atm += 1;
    }
    public void addCard(){
        card += 1;
    }
}
