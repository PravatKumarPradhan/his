angular.module('myApp').service('productService', ['$rootScope', function ($rootScope) {
	var productId = 0;
  var passStoreDataId = function(passStoreDataId){
    return passStoreDataId;
  }
  var setProductId = function(id) {
    productId = id;
  };
  var getProductId = function(){
    return productId;
  };
  var storeId = 0;
  var passStoreId = function(passStoreId){
    return passStoreId;
  }
  var setStoreId = function(id) {
    storeId = id;
  };
  var getStoreId = function(){
		return storeId;
  };
  return {
    passStoreDataId: passStoreDataId,
    setProductId: setProductId,
    getProductId: getProductId,
    passStoreId: passStoreId,
    setStoreId: setStoreId,
    getStoreId: getStoreId
  };
}]);