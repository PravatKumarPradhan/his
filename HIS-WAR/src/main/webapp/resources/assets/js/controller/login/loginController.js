'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:loginController
 * @description #loginController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'loginController',
				[
						'$scope',
						'$rootScope',
						'$state',
						'$http',
						'GenericService',
						'$sessionStorage',
						'$cookies',
						function($scope, $rootScope, $state, $http,
								GenericService,$sessionStorage,$cookies) {
							
							//for star camera
							
							  var _video = null,
						        patData = null;

						    $scope.patOpts = {x: 0, y: 0, w: 25, h: 25};

						    // Setup a channel to receive a video property
						    // with a reference to the video element
						    // See the HTML binding in main.html
						    $scope.channel = {};

						    $scope.webcamError = false;
						    $scope.onError = function (err) {
						        $scope.$apply(
						            function() {
						                $scope.webcamError = err;
						            }
						        );
						    };

						    $scope.onSuccess = function () {
						        // The video element contains the captured camera data
						        _video = $scope.channel.video;
						        $scope.$apply(function() {
						            $scope.patOpts.w = _video.width;
						            $scope.patOpts.h = _video.height;
						            //$scope.showDemos = true;
						        });
						    };

						    $scope.onStream = function (stream) {
						        // You could do something manually with the stream.
						    };

							$scope.makeSnapshot = function() {
						        if (_video) {
						            var patCanvas = document.querySelector('#snapshot');
						            if (!patCanvas) return;

						            patCanvas.width = _video.width;
						            patCanvas.height = _video.height;
						            var ctxPat = patCanvas.getContext('2d');

						            var idata = getVideoData($scope.patOpts.x, $scope.patOpts.y, $scope.patOpts.w, $scope.patOpts.h);
						            ctxPat.putImageData(idata, 0, 0);

						            sendSnapshotToServer(patCanvas.toDataURL());

						            patData = idata;
						        }
						    };
						    
						    /**
						     * Redirect the browser to the URL given.
						     * Used to download the image by passing a dataURL string
						     */
						    $scope.downloadSnapshot = function downloadSnapshot(dataURL) {
						        window.location.href = dataURL;
						    };
						    
						    var getVideoData = function getVideoData(x, y, w, h) {
						        var hiddenCanvas = document.createElement('canvas');
						        hiddenCanvas.width = _video.width;
						        hiddenCanvas.height = _video.height;
						        var ctx = hiddenCanvas.getContext('2d');
						        ctx.drawImage(_video, 0, 0, _video.width, _video.height);
						        return ctx.getImageData(x, y, w, h);
						    };

						    /**
						     * This function could be used to send the image data
						     * to a backend server that expects base64 encoded images.
						     *
						     * In this example, we simply store it in the scope for display.
						     */
						    var sendSnapshotToServer = function sendSnapshotToServer(imgBase64) {
						        $scope.snapshotData = imgBase64;
						    };
							
							//for end camera
							//1
							 //var offset = new Date().getTimezoneOffset(); //return -330
							 // var offset = -6;
							  /*alert(
									    new Date(
									      new Date().getTime() + offset * 3600 * 1000
									    ).toUTCString().replace( / GMT$/, "" )
									  );*/
							  
						     //  alert(Intl.DateTimeFormat().resolvedOptions().timeZone); // return Asia/Kolkata
							
							 
							 //2
							//vivek date and time start
							/*
							var Latitude  = 41.878114;
							var Longitude = -87.629798;*/
							
							/*$scope.getTimeUsingLatLng  = function(lat,lng)
							{
								var times_Stamp = (Math.round((new Date().getTime())/1000)).toString(); 
								  $.ajax({
								   url:"https://maps.googleapis.com/maps/api/timezone/json?location=" + lat + "," + lng + "&timestamp=" + times_Stamp,
								   cache: false,
								   type: "POST",
								   async: false,
								  }).done(function(response){

								  if(response.timeZoneId != null){
								   var Cur_Date = new Date();
								   var UTC = Cur_Date.getTime() + (Cur_Date.getTimezoneOffset() * 60000);
								   var Loc_Date = new Date(UTC + (1000*response.rawOffset) + (1000*response.dstOffset));
								       // $("#timeOfLocation").html("Time Of The Location Is " + Loc_Date.toLocaleString());
								  alert("Time Of The Location Is " + Loc_Date.toLocaleString());
								  // $scope.currentDate = moment(Loc_Date.toLocaleString()).format('DD-MM-YYYY HH:mm:ss');
								   //alert($scope.currentDate);
								      }
								    });
							}
							
							
							  if (navigator.geolocation) {
							        navigator.geolocation.getCurrentPosition(showPosition);
							    } else { 
							       alert("Geolocation is not supported by this browser.");
							    }

							  function showPosition(position) {
							     // x.innerHTML = "Latitude: " + position.coords.latitude + 
							      //"<br>Longitude: " + position.coords.longitude;
								  //alert(position.coords.latitude);
								  $scope.getTimeUsingLatLng(position.coords.latitude,position.coords.longitude);
							  }*/
							
							
							  
							//vivek date and time end
							
							$scope.name = "Vivek";
							 $rootScope.loginpage = false;	
							var data = "";
							var URI = BASE_URL + ROOT_URL + ORGANIZATIONLIST;
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.organizationList = [];
												if (response.data.status == "success") {
													$scope.organizationList = response.data.listObject;
												}
											});

							$scope.getUnit = function(val) {
								var data = {
									organizationId : val
								};
								
								  var URI = BASE_URL+ROOT_URL+UNITLIST;
								  GenericService.serviceAction("POST",URI,data).then(function(response){
									  
										$scope.unitList = [];
										if (response.data.status == "success") {
											$scope.unitList = response.data.listObject;
										}
										
								 
								 });
								 

							};
							
							$scope.login = function() {
								// $rootScope.loginpage= true;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}
								var URI = BASE_URL + ROOT_URL + LOGIN;
								GenericService.serviceAction("GET", URI, data)
										.then(function(response) {
											alert('Sucess');
											
											
										});
								 sessionStorage.setItem("organizationId",$scope.organizationId);
								 sessionStorage.setItem("unitId",$scope.unitId);
								 sessionStorage.setItem("userId",$scope.unitId);
								 //alert(sessionStorage.getItem("unitId"));
								 var cookieObject = {'organizationId':$scope.organizationId, 'unitId':$scope.unitId,'userId':1}
								 $cookies.putObject('cookieObject',cookieObject);	
								console.log(data);
								$scope.getUnitApplicationBillingConfigByOrgUnit($scope.unitId,$scope.organizationId);
								$state.go('bedManagement');

							};
							
							$scope.getUnitApplicationBillingConfigByOrgUnit = function(uId,oId) {
								  var URI = BASE_URL+ROOT_URL+"/api/unit/applicationConfiguration/billing/orgId/"+oId+"/unitId/"+uId;
								  GenericService.serviceAction("GET",URI,data).then(function(response){
									  
										$scope.unitList = [];
										if (response.data.status == "success") {
											 var cookieObjectAppConfig = {
													 'defaultSelfTariffId':response.data.object.defaultSelfTariffId, 
													 'isPreOrPostBilling':response.data.object.isPreOrPostBilling,
													 }
											 }
											 $cookies.putObject('cookieObjectAppConfig',cookieObjectAppConfig);	
							
								 });
								 

							};
							
							
							
							
						} ]);
