package com.example.bugbug.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("recipes")
public class Recipes {
    @Column("recipe_id")
    private int recipeId;
    @Column("user_id")
    private int userId;
    private String name;
    private String image;
    private String explan;
    private String point;
    @Column("image_blurred")
    private int imageBlurred;
    private int browes;
    @Column("reg_date_on")
    private Date regDateOn;
    private int deleted;
}
