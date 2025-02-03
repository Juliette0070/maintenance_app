app.directive("monMenu",function(){
    
    return{
        templateUrl : "vues/menuCommun.html" ,
        scope : {
            titre : "@", 
            btnVisible :"=",  
            isCollapsed :"@",
            srcMenu : "@",
    
            },
        link : function($scope){
          
            $scope.menu = function(){
                            $scope.isCollapsed = !$scope.isCollapsed;
                        };
        }
   };
});

app.directive("maBoiteConnexion",function(){
    
    return{
         templateUrl : "vues/boiteConnexion.html" ,
         scope : false
        
        
        
        
        
    };
    
    
    
    
    
    
    
});


