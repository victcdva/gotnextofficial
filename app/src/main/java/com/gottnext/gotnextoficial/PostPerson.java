package com.gottnext.gotnextoficial;

import android.graphics.drawable.Drawable;

public class PostPerson {


    //atributes
    private Drawable image;
    private String post;
    private Drawable imagenPerson;
    private String name;
    private String lastName;

    //get and set
    public Drawable getImage(){return image;}
    public void setImage(Drawable image){this.image = image;}

    public String getPost(){return post;}
    public void setPost(String Post){this.post = Post;}

    public Drawable getImagenPerson(){return imagenPerson;}
    public void setImagenPerson(Drawable imagenPerson) {this.imagenPerson = imagenPerson;}

    public String getName(){return name;}
    public void setName(String name) {this.name = name;}

    public String getLastName(){return lastName;}
    public void setLastName(String LastName){this.lastName = LastName;}

    //constructors

    public PostPerson(){
        this.image = null;
        this.post = "";
        this.imagenPerson = null;
        this.name = "";
        this.lastName = "";
    }
    public PostPerson(String Image, String Post, String ImagenPerson, String Name, String LastName){
        this.image = Imagen.ImagenString(Image);
        this.post = Post;
        this.imagenPerson = Imagen.ImagenString(ImagenPerson);
        this.name = Name;
        this.lastName = LastName;
    }

}
