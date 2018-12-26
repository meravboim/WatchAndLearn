package com.example.merav.myapplication;

public class teacher extends users {

    protected String area;
    protected String cost;
    protected String profession;
    protected String phone;
    protected String image="https://firebasestorage.googleapis.com/v0/b/myapplication-c864e.appspot.com/o/default_user.png?alt=media&token=257b33ff-4197-43b7-82af-74d6ad47e394";

    public teacher( String Mail ,String password, String name, String age, String area, String cost, String profession,String phone){
        this.age=age;
        this.email=Mail;
        this.name=name;
        this.password=password;
        this.area=area;
        this.cost=cost;
        this.profession=profession;
        this.phone=phone;
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
}
