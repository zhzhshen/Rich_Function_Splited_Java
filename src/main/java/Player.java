public class Player {
    private int cashBalance;

    public Player(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void buy(RealEstate realEstate) {
        if (realEstate.getOwner() == null && cashBalance >= realEstate.getPrice()){
            cashBalance -= realEstate.getPrice();
            realEstate.setOwner(this);
        }
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void build(RealEstate realEstate) {
        if (realEstate.getOwner().equals(this) && realEstate.build()) {
            cashBalance -= realEstate.getPrice();
        }
    }

    public void pay(RealEstate realEstate) {
        cashBalance -= realEstate.getPrice() * 0.5 * Math.pow(2, realEstate.getLevel());
    }
}
