angular.module('app').controller('sqlDefineCtrl', function($scope,$http,$uibModal) {
	
	$scope.pagination = {};
	$scope.queryparams = {};
 	$scope.sqldefine = {};
	$scope.search = function(){
		
		//广播分页条查询
		$scope.$broadcast("sqldefinegrid");  
	}
	
	$scope.sett = {
			data:{
				simpleData:{
				enable: true, //不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0}
			},
			callback: {
				onClick: function(event,treeId,node,idx){
					$scope.$broadcast("sqldefinegrid");  
				}
			}};
	 
	 $scope.openTemplate = function () {
        
          var modalInstance = $uibModal.open({
             templateUrl: '/search/sqldefine/edit',
             controller: 'sqlDefineCtrl',
             size:'lg',
             resolve: {
               items: function () {
                 return $scope.items;
               }
             }
         });
         
         modalInstance.result.then(function (selectedItem) {
             $scope.selected = selectedItem;
           }, function () {
             $log.info('Modal dismissed at: ' + new Date());
           });
     };
     
    $scope.cancel = function() {
    	 $scope.$dismiss();
  	};
  	
  	$scope.save = function() {
  		$http.post('/search/sqldefine/save',$scope.sqlgroup).success(function(data){
  			$scope.$broadcast("sqldefinegrid");  
  			$scope.$close();
  		});	
 	};
 
});