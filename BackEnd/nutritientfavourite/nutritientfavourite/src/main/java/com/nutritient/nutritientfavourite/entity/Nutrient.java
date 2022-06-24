package com.nutritient.nutritientfavourite.entity;

public class Nutrient {
    private String number;
    private String name;
    private long amount;
    private String unitName;
    private String derivationCode;
    private String derivationDescription;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDerivationCode() {
        return derivationCode;
    }

    public void setDerivationCode(String derivationCode) {
        this.derivationCode = derivationCode;
    }

    public String getDerivationDescription() {
        return derivationDescription;
    }

    public void setDerivationDescription(String derivationDescription) {
        this.derivationDescription = derivationDescription;
    }
}
