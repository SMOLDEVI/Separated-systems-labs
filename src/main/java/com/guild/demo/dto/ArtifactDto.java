package com.guild.demo.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArtifactDto {
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @NotBlank(message = "Стихия обязательна")
    private String element;

    @NotNull
    @Min(value = 1, message = "Уровень силы должен быть больше 0")
    private Integer powerLevel;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getElement() { return element; }
    public void setElement(String element) { this.element = element; }
    public Integer getPowerLevel() { return powerLevel; }
    public void setPowerLevel(Integer powerLevel) { this.powerLevel = powerLevel; }
}