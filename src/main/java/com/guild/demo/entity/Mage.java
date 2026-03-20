package com.guild.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "mages")
public class Mage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String rank;
    private Integer manaPool;

    public Mage() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
    public Integer getManaPool() { return manaPool; }
    public void setManaPool(Integer manaPool) { this.manaPool = manaPool; }
}