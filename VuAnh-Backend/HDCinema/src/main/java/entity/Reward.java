package entity;



public class Reward {
    private String name;
    private int point; 
    private String img; 

    // Constructor
    public Reward(String name, int point, String img) {
        this.name = name;
        this.point = point;
        this.img = img;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // toString method
    @Override
    public String toString() {
        return "Reward{" +
                "name='" + name + '\'' +
                ", point=" + point +
                ", img='" + img + '\'' +
                '}';
    }
}
