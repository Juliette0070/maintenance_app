/*-------------------------Connexion------------------------*/
app.controller('connexionController',function($scope,$location,$http ){
     
     $scope.lblLogin = "Login";
     $scope.lblMdp = "Mot de passe";
     $scope.lblBtn = "Valider";
     $scope.lblMessage = "erreur de login ou de mot de passe";
     $scope.msgErreur = false;
     $scope.valider = function(){
         var login = $scope.login;
         var mdp = $scope.mdp;
         var req = {
            method: 'POST',
            url: 'ajax/traiterconnexion.php',
            data: { login : login, mdp : mdp}
        };
      
        $http(req)
            .then(function (response) {
                var visiteur = response.data;
                if(visiteur.nom == undefined){
                    
                      $scope.msgErreur = true;
                }
                else{
                     $scope.msgErreur = false;
                     $location.url("accueil");
                }
            });
     };
});

/*-----------------------Accueil------------------------------*/

app.controller('accueilController',function(){
   
   
});
/*-----------------------Medecins------------------------------*/

app.controller('medecinsController',function($scope,$http, monMedecinFactory){
    
 //   $scope.titre = "Gestion des médecins";
  //  $scope.btnVisible = true;
 //   $scope.isCollapsed = true;
 //   $scope.srcMenu = "vues/menuMedecins.html";
//    $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
  //   $scope.med={};
    $scope.rechercheMedecin ={};           
    $scope.chargerMedecins = function(){
    var nomMedecin = $scope.rechercheMedecin.nom;
    if(nomMedecin.length >1){
            var req = {
                    method: 'POST',
                    url: 'ajax/traiterrecherchemedecins.php',
                    data: { nomMedecin : nomMedecin}
                    };
                $http(req)
                    .then(function (response) {
                        var lesMedecins = response.data;
                        $scope.medecins = lesMedecins;
                    });
            }    
        };
   
    $scope.choisirMedecin = function(medecin){
            $scope.rechercheMedecin.nom = medecin.nom + " "+medecin.prenom;
            $scope.medecins ={};// vide la liste;
         //   $rootScope.medecin = medecin; //enregistrement pour communiquer avec un autre contrôleur
            monMedecinFactory.set(medecin);
                   
        
        } ;
});

/*-----------------------------------Controleur MajMedecin--------------------------------*/
app.controller('majMedecinController',function($scope, $http,$location, monMedecinFactory){
    //   var medecin = $scope.medecin; // héritage du rootScope
    var medecin = monMedecinFactory.get();
    if(medecin == undefined)
         $location.url("medecins");
     else{
//        $scope.srcMenu = "vues/menuMedecins.html";
//        $scope.btnVisible = true;
//        $scope.titre = "Mise à jour";
//        $scope.isCollapsed = true;
//        $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
        $scope.lblAdresse = "Adresse";
        $scope.lblTel = "Téléphone";
        $scope.lblSpecialite = "Spécialité complémentaire ";
        $scope.lblEnvoyer = "Mettre à jour";
        $scope.m = {};
        $scope.m.adresse = medecin.adresse;
        $scope.m.tel = medecin.tel;
        $scope.m.specialite = medecin.specialitecomplementaire;
        $scope.valider  = function(){
              var req = {
                method: 'POST',
                url: 'ajax/traitermajmedecin.php',
                data: { 
                    id : medecin.id,
                    adresse : $scope.m.adresse,
                    tel : $scope.m.tel,
                    specialite : $scope.m.specialite
                    }
                };
          $http(req)
                .then(function (response) {
                    var message ="";
                    if(response.data==1){
                        message = "Médecin mis à jour";
                        $scope.typeMessage = "alert alert-success";
                    }
                    else{
                        message = "Veuillez réessayer plus tard..."; 
                        $scope.typeMessage = "alert alert-danger";
                    }
                   $scope.message = message;
                     });
                     

          };
     }
 });
 
 /*---------------------------------Controleur DerniersRapports---------------------*/
app.controller('derniersRapportsController',function($scope, $location, $http,monMedecinFactory){
     var medecin = monMedecinFactory.get();
     if(medecin == undefined)
         $location.url("medecins");
     else{
//       $scope.srcMenu = "vues/menuMedecins.html";
//       $scope.appelTel = "tel:" + medecin.tel;
//       $scope.btnVisible = true;
//       $scope.titre = "Les derniers rapports";
//       $scope.isCollapsed = true;
//       $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
        var req = {
            method: 'POST',
            url: 'ajax/traitergetlesrapports.php',
            data: { idMedecin : medecin.id}
        };
        $http(req)
            .then(function (response) {
                var lesRapports = response.data;
                if(lesRapports.length == 0){
                   $scope.message = "désolé, pas de rapport pour ce médecin..."
                   $scope.typeMessage = "alert alert-success";
               }
                else{
                   $scope.rapports = lesRapports;
                }
                    
            });
     }    
 });
 
/*-----------------------Rapports------------------------------*/
app.controller('rapportsController',function($scope){
//    $scope.titre = "Gestion des visites";
//    $scope.btnVisible = true;
//    $scope.isCollapsed = true;
//    $scope.srcMenu = "vues/menuRapports.html";
//    $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
  
});
/*-----------------------------------Controleur ChoisirRapport----------------------------------*/

app.controller('choisirRapportController',function($scope, $http, maDateFactory){
//     $scope.btnVisible = true;
//     $scope.titre = "Choisir le rapport à modifier";
//     $scope.srcMenu = "vues/menuRapports.html";
//     $scope.isCollapsed = true;
//     $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
      $scope.lblDateRapport = "Sélectionner la date du rapport recherché";             
//     $scope.changementDate = function(){
//         $scope.rapports = [
//                            {id:12,nomMedecin:"durand",prenomMedecin:"jean"},
//                            {id:13,nomMedecin:"dupond",prenomMedecin:"Anne"}
//                            ];
//     };
     

    $scope.changementDate = function(){
        $scope.message  ="";
        var dateRapport = $scope.dateRapport;
        var date = maDateFactory.get(dateRapport);
       
      
//        var jour = dateRapport.getDate();
//        var mois = dateRapport.getMonth() + 1 ;
//        var annee = dateRapport.getFullYear();
//        var date = annee+'-'+mois+'-'+jour;
        var req = {
                    method: 'POST',
                    url: 'ajax/traiterlesvisitesaunedate.php',
                    data: { dateRapport : date}
                  };

        $http(req)
            .then(function (response) {
                var lesRapports = response.data;
                if(lesRapports.length == 0)
                   $scope.message = "désolé, pas de rapport ce jour..."
                else{
                   $scope.rapports = lesRapports;
                }
            });
     };   
      
});
/*--------------------------------------- MajRapport-----------------------------*/

app.controller('majRapportController',function($scope,$location,$http){
    
//      $scope.btnVisible = true;
//      $scope.isCollapsed = true;
//      $scope.titre = "Mise à jour du rapport";
//      $scope.srcMenu = "vues/menuRapports.html";
//      $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
      $scope.lblMotif = "Motif :";
      $scope.lblBilan = "Bilan :";
      $scope.lblEnvoyer = "Envoyer";
     
      
      var id = $location.search().id;
      $scope.nomMedecin = $location.search().nom;
      $scope.prenomMedecin = $location.search().prenom;
      var req = {
            method: 'POST',
            url: 'ajax/traiterchoixrapport.php',
            data: { id : id}
            };
      $http(req)
            .then(function (response) {
                var leRapport = response.data;
                var motif = leRapport.motif;
                var bilan = leRapport.bilan;
                $scope.bilan = bilan;
                $scope.motif = motif;
            });
     
      $scope.valider  = function(){
          var req = {
            method: 'POST',
            url: 'ajax/traitermajrapport.php',
            data: { 
                id : id,
                bilan : $scope.bilan,
                motif : $scope.motif
                }
            };
      $http(req)
            .then(function (response) {
                if(response.data==1)
                    alert("Rapport mis à jour");
                else
                    alert("Veuillez réessayer plus tard...");
                 });
      
      };
 });
 //-----------------------------------------Nouveau rapport-------------------------------------------//
 app.controller('nouveauRapportController',function($scope, $http,$rootScope,maDateFactory){
//    $scope.titre = "Ajout d'un rapport";
//    $scope.btnVisible = true;
//    $scope.isCollapsed = true;
//    $scope.srcMenu = "vues/menuRapports.html";
//    $scope.menu = function(){
//                        $scope.isCollapsed = !$scope.isCollapsed;
//                  };
    $scope.lblMotif = "Motif de la visite";
    $scope.lblBilan = "Bilan de la visite";
    $scope.lblTitreMedicament= "Médicaments offerts";
    $scope.lblEnvoyer="Enregistrer le rapport";
    $scope.lblQte ="Nombre d'exemplaires";
    $scope.lblDate ="Date de la visite";
    $scope.lblAjouterMedicament = "Ajouter";
    $scope.lblRetirerMedicament = "Retirer";
    $scope.r = {};
     
   
    $scope.rechercheMedecin={};              
    $scope.chargerMedecins = function(){
            var nomMedecin = $scope.rechercheMedecin.nom;
            console.log($scope);
            if(nomMedecin.length >1){
                var req = {
                    method: 'POST',
                    url: 'ajax/traiterrecherchemedecins.php',
                    data: { nomMedecin : nomMedecin}
                    };
                $http(req)
                    .then(function (response) {
                        var lesMedecins = response.data;
                        $scope.medecins = lesMedecins;
                    });
            }    
        };
   
    $scope.choisirMedecin = function(medecin){
            $scope.rechercheMedecin.nom = medecin.nom + " "+medecin.prenom;
            $scope.medecins ={};// vide la liste;
            $rootScope.medecin = medecin; //enregistrement pour communiquer avec un autre contrôleur
     
            } ;
       // Sélection des medicaments
    $scope.rechercheMedicament={};
    $scope.chargerMedicaments = function(){
            var nomMedicament = $scope.rechercheMedicament.nom;
            if(nomMedicament.length > 0){
                var req = {
                    method: 'POST',
                    url: 'ajax/traiterrecherchemedicaments.php',
                    data: { nomMedicament : nomMedicament}
                    };
                $http(req)
                    .then(function (response) {
                        var lesMedicaments = response.data;
                        $scope.medicaments = lesMedicaments;
                    });
            }    
        };
    $scope.choisirMedicament = function(data){
            $scope.medicament = data;
            $scope.rechercheMedicament.nom = $scope.medicament.nomCommercial;
            $scope.medicaments ={};// vide la liste;
        } ;
    $scope.qtes = ["1","2","3","4","5","6"];
    $scope.medicamentsSelect = [];
    $scope.data={};
    $scope.ajouter = function(){
        if($scope.rechercheMedicament.nom != undefined &&
                $scope.data.qteSelect != undefined    ){
                $scope.medicamentsSelect.push({
                        nom:$scope.rechercheMedicament.nom,
                        qte:$scope.data.qteSelect,
                        idMedicament : $scope.medicament.id
                        });
                $scope.rechercheMedicament.nom = undefined; 
                $scope.data.qteSelect = undefined;
                }
         };
    $scope.retirer = function(){
        if($scope.medicamentsSelect.length>0)
                $scope.medicamentsSelect.pop();
    };
    $scope.enregistrer = function(){
              var dateRapport = $scope.r.date;
              var date = maDateFactory.get(dateRapport);
//              var jour = dateRapport.getDate();
//              var mois = dateRapport.getMonth() + 1 ;
//              var annee = dateRapport.getFullYear();
//              var date = annee+'-'+mois+'-'+jour;
              var req = {
                    method: 'POST',
                    url: 'ajax/traiterajouterrapport.php',
                    data: { 
                        idMedecin : $scope.medecin.id,
                         motif : $scope.r.motif,
                         bilan : $scope.r.bilan,
                         date : date,
                        lesMedicaments : $scope.medicamentsSelect
                         }
                    };
                $http(req)
                    .then(function (response) {
                           var message ="";
                            if(response.data==1){
                                message = "Rapport enregistré";
                                $scope.typeMessage = "alert alert-success";
                            }
                            else{
                                message = "Veuillez réessayer plus tard..."; 
                                $scope.typeMessage = "alert alert-danger";
                            }
                           $scope.message = message;
                   
                    
                    
                    });
            
        };
        
});

