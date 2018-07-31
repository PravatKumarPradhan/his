angular.module("myApp").factory('itemFactory',['$rootScope','$http','growl',function($rootScope,$http,growl){
	var scope = angular.element(document.getElementById('uiView')).scope();
	
	scope.setScope = function(){
		scope = angular.element(document.getElementById('uiView')).scope();
		return scope;
	}
	
	scope.setOrgUnit = function(orgId,unitId){
		scope.orgId = orgId;
		scope.unitId = unitId;
	}
	
	scope.initItemFactory = function(){
		var URI = "http://predikly-dev.tk:5002/HIS-WAR/api/global/assets/categories/1";
		var httpObject = {method : "GET",url : URI,data : null};
		$http(httpObject).then(function(response) {
			if(response.status == 200)
				scope.assetCategoryList = response.data;
		});
	}
	
	scope.getProductCatByAssestCat = function(assetCategoryId){
		if(assetCategoryId != "" && typeof(assetCategoryId) != "undefined"){
			var URI = "http://predikly-dev.tk:5002/HIS-WAR/api/global/products/categories/"+assetCategoryId;
			var httpObject = {method : "GET",url : URI,data : null};
			$http(httpObject).then(function(response) {
				if(response.status == 200)
					scope.productCategoryList = response.data;
			});
		}
	}
	
	scope.itemSearch = function(itemObj){
		var URI = "http://predikly-dev.tk:5002/HIS-WAR/api/global/items";//BASE_URL + ROOT_URL + "/api/global/items";
		var httpObject = {method : "POST",url : URI,data : itemObj};
		$http(httpObject).then(function(response) {
			if(response.status == 200)
				scope.searchItemList = response.data;
		});
		itemObj = {};
	}
	
	scope.selectAllItems = function(flag){
		angular.forEach(scope.searchItemList,function(value,key){
			value.isSelected = flag;
		});
	}
	
	return scope;
}]);	