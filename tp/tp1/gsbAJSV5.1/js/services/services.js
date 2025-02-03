

app.factory('maDateFactory',function(){
        var date = {
                    get : function(uneDate){
                                var jour = uneDate.getDate();
                                var mois = uneDate.getMonth() + 1 ;
                                var annee = uneDate.getFullYear();
                                var newDate = annee + '-' + mois + '-' + jour;
                                return newDate;
                            }
                     } 
        return date
});

app.factory('monMedecinFactory', function(){
      var medecin = {
                        _medecin : null,
                        set : function(leMedecin){
                                    this._medecin = leMedecin;
                                },
                        get : function(){
                            return this._medecin;
                        }
                    };
      return medecin;
});

app.factory('monVisiteurFactory', function(){
    var promise ={}
    
    
    
    
    return promise;
});
