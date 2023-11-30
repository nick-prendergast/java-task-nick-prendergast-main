package com.example.warehouse.model;

import java.time.LocalDate;

public class Asset {

    private String serialNumber;
    private String assetType;
    private LocalDate warrantyExpirationDate;

    public Asset(String serialNumber, String assetType, LocalDate warrantyExpirationDate) {
        this.serialNumber = serialNumber;
        this.assetType = assetType;
        this.warrantyExpirationDate = warrantyExpirationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssetType() {
        return assetType;
    }

    public LocalDate getWarrantyExpirationDate() {
        return warrantyExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return serialNumber.equals(asset.serialNumber);
    }

    @Override
    public int hashCode() {
        return serialNumber.hashCode();
    }

}