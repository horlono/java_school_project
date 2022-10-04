Iteration 3 :
Mettre en commentaire dans la classe DBTrello la ligne "initData(conn)"

lors de la suppression de colonne : message => Resulset Closed 
mais les changements dans la base de données


Iteration 2:

A.Concernant Iteration 1 
Application fonctionnant pleinement : aucun bug de deplacement , de suppression ou d'ajout , ainsi qu'une classe EditeLabel fonctionnel

Fonctionnalité esthetique adapté mais  à ameliorer : padding , size de l'appli  

B Concernant Iteration 2 :
Application fonctionnant pleinement : (aucun bug de deplacement , de suppression ou d'ajout) fonctionnalité undo redo operationnelle pour toutes les commandes


Bug majeur: EditaLabel => label non modifiable => NullPointException du commandManager
pour les test du undo redo : label dans les classes ColumnView et cardView (commande editable que dans ColumnView pour montrer son fonctionnement)

Malgré le plein fonctionnement de l'application de nombreux changements ergonomique vont etre effectué visant une plus grande lisibilité du code et optimisation des binding

