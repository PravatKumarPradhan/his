angular.module('myApp').service('GenericService', [ '$http','$rootScope', function($http,$rootScope) {
	
		this.serviceAction = function(httpVerb, url, obj) {
			var httpObject = {
				method : httpVerb,
				url : url,
				data : obj
			};
			//console.log(url,JSON.stringify(obj));
			var promise = $http(httpObject).then(function(response) {
				
				return response;
				
			});
			return promise;
			
		};

				
	}]);

/*angular.module('myApp').service('GenericServiceParam', [ '$http','$rootScope', function($http,$rootScope) {
	
	this.serviceAction = function(httpVerb, url, obj, notificationStatus,paramObj) {
		var httpObject = {
			method : httpVerb,
			url : url,
			data : obj,
			params:paramObj
		};
		//console.log(url,JSON.stringify(obj));
		var promise = $http(httpObject).then(function(response) {
			if (response.data.status == 'success')
			{
				if(notificationStatus == true)
				growl.success(response.data.message,{
					title : SUCCESS_MSG
				});
			} else if(response.data.status == 'error')
			{	
				if(response.data.code == 409){
					growl.warning(response.data.message,{
						title : WARNING_MSG
					});
				}else{
					growl.error(response.data.message,{
						title : ERROR_MSG
					});
				}
			}
			$rootScope.stopLoader();
			return response;
			
		},function(e) {
			$rootScope.stopLoader();
			growl.error(NETWORK_ERROR_MSG,{
				title : ERROR_MSG
			});
		});
		return promise;
		
	};

			
}]);

angular.module('myApp').service('GenericServiceHeader', [ '$http','$rootScope', function($http,$rootScope) {
	
	this.serviceAction = function(httpVerb, url, obj, notificationStatus,headerObj) {
		var httpObject = {
			method : httpVerb,
			url : url,
			data : obj,
			headers:headerObj
		};
		//console.log(url,JSON.stringify(obj));
		var promise = $http(httpObject).then(function(response) {
			if (response.data.status == 'success')
			{
				if(notificationStatus == true)
				growl.success(response.data.message,{
					title : SUCCESS_MSG
				});
			} else if(response.data.status == 'error')
			{	
				if(response.data.code == 409){
					growl.warning(response.data.message,{
						title : WARNING_MSG
					});
				}else{
					growl.error(response.data.message,{
						title : ERROR_MSG
					});
				}
			}
			$rootScope.stopLoader();
			return response;
			
		},function(e) {
			$rootScope.stopLoader();
			growl.error(NETWORK_ERROR_MSG,{
				title : ERROR_MSG
			});
		});
		return promise;
		
	};

			
}]);*/

angular.module('myApp').service('GenericServiceParamHeader', [ '$http','$rootScope','growl', function($http,$rootScope,growl) {
	
	this.serviceAction = function(httpVerb, url, obj, notificationStatus,paramObj,headerObj) {
		var httpObject = {
			method : httpVerb,
			url : url,
			data : obj,
			params:paramObj,
			headers:headerObj
		};
		//console.log(url,JSON.stringify(obj));
		var promise = $http(httpObject).then(function(response) {
			console.log(response);
			if (response.data.status == 'success')
			{
				if(notificationStatus == true)
				growl.success(response.data.message,{
					title : SUCCESS_MSG
				});
			} else if(response.data.status == 'error')
			{	
				if(response.data.code == 409){
					growl.warning(response.data.message,{
						title : WARNING_MSG
					});
				}else{
					growl.error(response.data.message,{
						title : ERROR_MSG
					});
				}
			}
			$rootScope.stopLoader();
			return response;
			
		},function(e) {
			$rootScope.stopLoader();
			growl.error(NETWORK_ERROR_MSG,{
				title : ERROR_MSG
			});
		});
		return promise;
		
	};

			
}]);

angular.module('myApp').service('BillingGenericService', [ '$http','$rootScope','growl', function($http,$rootScope,growl) {
	
	this.serviceAction = function(httpVerb, url, obj, notificationStatus) {
		var httpObject = {
			method : httpVerb,
			url : url,
			data : obj
		};
		//console.log(url,JSON.stringify(obj));
		var promise = $http(httpObject).then(function(response) {
			if (response.data.status == 'success')
			{
				if(notificationStatus == true)
				growl.success(response.data.message,{
					title : SUCCESS_MSG
				});
			} else if(response.data.status == 'error')
			{	
				if(response.data.code == 409){
					growl.warning(response.data.message,{
						title : WARNING_MSG
					});
				}else{
					growl.error(response.data.message,{
						title : ERROR_MSG
					});
				}
			}
			$rootScope.stopLoader();
			return response;
			
		},function(e) {
			$rootScope.stopLoader();
			growl.error(NETWORK_ERROR_MSG,{
				title : ERROR_MSG
			});
		});
		return promise;
		
	};

			
}]);