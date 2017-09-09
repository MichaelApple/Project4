package models.entity.brigades;

/**
 * Created by Miha on 09.09.2017.
 */
public abstract class Brigade {
    String name;
    int count;

    public abstract void work();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
