angular.module('myApp').directive('modal', function () {
    return {
      template: '<div id="confirmLogout" class="modal fade ng-scope in" role="dialog">'+
    		'<div class="modal-dialog">'+
		'<div class="modal-content">'+
			'<div class="modal-header">'+
				'<div class="modal-title patient-details-row">'+
					'Confirm'+
					'<button type="button" class="close" data-dismiss="modal">&times;</button>'+
				'</div>'+
			'</div>'+
			'<div class="modal-body">'+
				'<div class="margin-left10"><h5>Are you sure you want to Save?</h5></div>'+
			'</div>'+
			'<div class="modal-footer" style="border:none;">'+
				'<button type="button" style="width:auto;" ng-click="PopupOkBtn()" data-dismiss="modal" class="btn btn-primary"  id="">Ok</button>'+ 
				'<button type="button" style="width:auto;" data-dismiss="modal" class="btn btn-primary">Cancel</button>'+
			'</div>'+
		'</div>'+
	'</div>'+
'</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
          scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });