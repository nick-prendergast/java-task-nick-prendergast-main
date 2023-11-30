package com.example.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarehouseApiTest {

	private WarehouseApi api;

	@BeforeEach
	void setUp() {
		api = new WarehouseApi();
		executeWarehouseOperations(api);
	}

	private void executeWarehouseOperations(WarehouseApi api) {
		api.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "13/12/2016");
		api.addAsset("CSDD22DSS4SWF", "TABLET", "22/12/2030");
		api.addAsset("SDSCS5W32D222", "COMPUTER", "21/12/2030");
		api.addAsset("DSS23532323SD", "COMPUTER", "20/12/2030");
		api.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "13/12/2016");
		api.addAsset("CSDD23DSSDBBF", "TABLET", "14/10/2029");
		api.addAsset("DSS23231323SD", "COMPUTER", "22/11/2031");
		api.addAsset("CXXWE2EWXCXCV", "MOBILE_PHONE", "03/03/2030");
		api.addAsset("VSADW5WEEEWWW", "WATCH", "10/02/2017");
		api.addAsset("VSADAAA005WWW", "TV", "22/12/2031");
		api.addAsset("CXXWE23C52XCV", "TV", "03/01/2034");
		api.addAsset("WDFGSDDC52XCV", "TV", "03/01/2034");
		api.addAsset("VSA43A56EEEWR", "WATCH", "22/12/2031");
		api.addAsset("OGSDXWE23C523", "TV", "01/01/2030");
		api.addAsset("VSA43A56EEEWR", "WATCH", "22/12/2031");

		api.removeAsset("CXXWEW8WXCXCV");
		api.removeAsset("WDFGSDDC52XCV");
		api.removeAsset("OGSDXWE23C523");
		api.removeAsset("USDSD33SD3343");
	}

	@Test
	void shouldReturnTotalNumberOfAssetsInWarehouse() {
		// when
		int totalAssets = api.countAllAssets();

		// then
		assertThat(totalAssets).isEqualTo(10);
	}

	@Test
	void shouldReturnTotalNumberOfComputersInWarehouse() {
		// when
		int totalComputers = api.countByAssetType("COMPUTER");

		// then
		assertThat(totalComputers).isEqualTo(3);
	}

	@Test
	void shouldReturnTotalNumberOfWatchesInWarehouse() {
		// when
		int totalWatches = api.countByAssetType("WATCH");

		// then
		assertThat(totalWatches).isEqualTo(2);
	}

	@Test
	void shouldReturnTotalNumberOfTabletsInWarehouse() {
		// when
		int totalTablets = api.countByAssetType("TABLET");

		// then
		assertThat(totalTablets).isEqualTo(2);
	}

	@Test
	void shouldReturnTotalNumberOfMobilePhonesInWarehouse() {
		// when
		int totalMobilePhones = api.countByAssetType("MOBILE_PHONE");

		// then
		assertThat(totalMobilePhones).isOne();
	}

	@Test
	void shouldReturnTotalNumberOfServersInWarehouse() {
		// when
		int totalServers = api.countByAssetType("SERVER");

		// then
		assertThat(totalServers).isZero();
	}

	@Test
	void shouldReturnTotalNumberOfTvsInWarehouse() {
		// when
		int totalTvs = api.countByAssetType("TV");

		// then
		assertThat(totalTvs).isEqualTo(2);
	}

	@Test
	void shouldFindAssetInWarehouse() {
		// when
		boolean assetInWarehouse = api.isAssetInWarehouse("VSA43A56EEEWR");

		// then
		assertThat(assetInWarehouse).isTrue();
	}

	@Test
	void shouldFindExpiredWarrantyAssetsSerialNumbers() {
		// when
		List<String> expiredWarrantyAssetsSerialNumbers = api.findExpiredWarrantyAssetsSerialNumbers();

		// then
		assertThat(expiredWarrantyAssetsSerialNumbers).containsOnly("VSADW5WEEEWWW");
	}

	@Test
	void shouldFindAllCorporateAssetsSerialNumbers() {
		// when
		List<String> corporateAssetsSerialNumbers = api.findCorporateAssetSerialNumbers();

		// then
		assertThat(corporateAssetsSerialNumbers).hasSize(4);
	}

	@Test
	void shouldFindCorrectCorporateSerialNumbers() {
		// when
		List<String> corporateAssetsSerialNumbers = api.findCorporateAssetSerialNumbers();

		// then
		assertThat(corporateAssetsSerialNumbers)
				.isNotEmpty()
				.allMatch(this::isCorporateSerialNumber);
	}

	private boolean isCorporateSerialNumber(String serialNumber) {
		return serialNumber.startsWith("C");
	}
}
