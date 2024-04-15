package com.ptit.Entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name="ID_Category")
    private String idCategory;

    @Column(name = "Category_Name")
    private String categoryName;

    @Column(name = "Describe")
    private String describe;

//    @OneToMany(mappedBy= "idCategory")
//    private Set<Post> posts;

    public Category() {

    }

    public Category(String category_Name, String describe) {
        this.categoryName = category_Name;
        this.describe = describe;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
