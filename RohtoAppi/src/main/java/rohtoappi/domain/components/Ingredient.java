package rohtoappi.domain.components;

/**
 * Luokka kuvaa ainesta, jonka voi laittaa rohtoon. Aineksella on nimi,
 * mittayksikkö ja mahdollisesti asetettu määrä.
 *
 */
public class Ingredient implements Comparable<Ingredient> {

    private String name;
    private String measuringUnit;
    private int amount;

    public Ingredient(String name, String measuringUnit) {
        this.name = name;
        this.measuringUnit = measuringUnit;
        this.amount = 0;
    }

    public Ingredient(String name, int amount, String measuringUnit) {
        this.name = name;
        this.measuringUnit = measuringUnit;
        this.amount = amount;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        Ingredient i = (Ingredient) o;
        if (this.name.equals(i.name) && this.measuringUnit.equals(i.measuringUnit)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        if (amount > 0) {
            return name + "\t\t" + amount + " " + this.measuringUnit;
        }
        return name + "\t\t" + " " + this.measuringUnit;
    }

    @Override
    public int compareTo(Ingredient t) {
        return t.getName().compareToIgnoreCase(this.getName());
    }

}
