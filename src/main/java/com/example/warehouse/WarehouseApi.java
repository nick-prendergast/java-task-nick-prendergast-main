package com.example.warehouse;

import com.example.warehouse.model.Asset;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class WarehouseApi {

    private final Map<String, Asset> assets = new HashMap<>();

    public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {
        LocalDate warrantyDate = LocalDate.parse(warrantyExpirationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (serialNumber.startsWith("C")) {
            warrantyDate = warrantyDate.plusYears(1);
        }
        Asset asset = new Asset(serialNumber, assetType, warrantyDate);
        assets.put(serialNumber, asset);
    }


    public void removeAsset(String serialNumber) {
        assets.remove(serialNumber);
    }

    public boolean isAssetInWarehouse(String serialNumber) {
        return assets.containsKey(serialNumber);
    }
    public int countAllAssets() {
        return assets.size();
    }
    public int countByAssetType(String assetType) {
        return (int) assets.values().stream()
                .filter(asset -> asset.getAssetType().equals(assetType))
                .count();
    }

    public List<String> findExpiredWarrantyAssetsSerialNumbers() {
        LocalDate today = LocalDate.now();
        return assets.values().stream()
                .filter(asset -> asset.getWarrantyExpirationDate().isBefore(today))
                .map(Asset::getSerialNumber)
                .collect(Collectors.toList());
    }

    public List<String> findCorporateAssetSerialNumbers() {
        return assets.values().stream()
                .filter(asset -> asset.getSerialNumber().startsWith("C"))
                .map(Asset::getSerialNumber)
                .collect(Collectors.toList());
    }
}