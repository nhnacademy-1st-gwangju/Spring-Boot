package com.nhnacademy.conditional.component;

public class SayYesComponent{
    private String name;

    public SayYesComponent(String name) {
        this.name = name;
    }

    public void sayYes() {
        System.out.println("YES! " + this.name);
    }
}