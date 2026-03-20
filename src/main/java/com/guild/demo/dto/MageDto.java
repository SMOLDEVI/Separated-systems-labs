package com.guild.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MageDto {

    @NotBlank(message = "Имя мага не может быть пустым")
    private String name;

    @NotBlank(message = "Ранг мага обязателен (например: Ученик, Мастер, Архимаг)")
    private String rank;

    @NotNull(message = "Запас маны должен быть указан")
    @Min(value = 0, message = "Запас маны не может быть отрицательным")
    private Integer manaPool;

    public MageDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getManaPool() {
        return manaPool;
    }

    public void setManaPool(Integer manaPool) {
        this.manaPool = manaPool;
    }
}