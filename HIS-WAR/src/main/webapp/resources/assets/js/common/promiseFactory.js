//Promise Factory
//Code Added By Ganesh 04.10.2017
angular.module('myApp').factory('promiseFactory', function($http, $q) 
		{
	var promises = [];
	// var promise2;
	  return {
		setPromises : function(URI1,URI2,methodType1,methodType2) {
			var deffered = $q.defer();
			var deffered1 = $q.defer();
			$http({ method: methodType1, url: URI1 }).success(function(data){
		        deffered.resolve(data);
		      });// then(function(data) {return data;});
			$http({ method: methodType2, url: URI2 }).success(function(data){
		        deffered1.resolve(data);
		      });
			promises = [];
			promises.push(deffered.promise);
			promises.push(deffered1.promise);
			return $q.all(promises);
			
		}
	  }
});

angular.module('myApp').factory('promiseFactoryWithObject', function($http, $q) 
		{
	var promises = [];
	// var promise2;
	  return {
		setPromisesWithObject : function(URI1,URI2,methodType1,methodType2,obj1,obj2) {
			var deffered = $q.defer();
			var deffered1 = $q.defer();
			$http({ method: methodType1, url: URI1, data : obj1}).success(function(data){
		        deffered.resolve(data);
		      });// then(function(data) {return data;});
			$http({ method: methodType2, url: URI2, data : obj2}).success(function(data){
		        deffered1.resolve(data);
		      });
			promises = [];
			promises.push(deffered.promise);
			promises.push(deffered1.promise);
			return $q.all(promises);
			
		}
	  }
});