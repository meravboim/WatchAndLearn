package com.example.merav.myapplication;

public class teacher extends users {

    protected float rank;
    protected int count;
    protected String area;
    protected String cost;
    protected String profession;
    protected String phone;
    protected String image;

    public teacher( String Mail ,String password, String name, String age, String area, String cost, String profession,String phone,float rank,String image){
        this.age=age;
        this.email=Mail;
        this.name=name;
        this.password=password;
        this.area=area;
        this.cost=cost;
        this.profession=profession;
        this.phone=phone;
        this.rank=rank;
        this.image=image;
        this.type="teacher";
    }
    public teacher(){}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return "Name: " + name+ '\n' +
                "Area: " + area + '\n' +
                "Cost: " + cost + '\n' +
                "Profession: " + profession + '\n' +
                "Phone number: " + phone + '\n';
    }
}
