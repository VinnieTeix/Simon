#Projet Simon

Le but de ce projet permet de reproduire le jeu de société "Simon" sur Java


#Les Main() à démarrer

- Le client et son interface s'exécute sur la classe MainClient
- Le serveur s'exécute sur la classe MainServer


#Fonctionnement du Client envisagé

- Le client appuiera sur chaque bouton et pour chaque bouton appuyé, il ajoutera cette couleur dans un ArrayList. //Fonctionnel
 - Pour chaque couleur entrée dans cet ArrayList, la case sera comparée en temps réel avec celle envoyé par le Serveur


#Fonctionnement du Serveur envisagé

- Le serveur enverra en premier la première séquence à effectué sous forme d’un ArrayList.
- Il sera responsable de la comparaison de l’ArrayList envoyé par le Client avec le sien et déterminer si la séquence effectuée par le joueur est correcte ou non //Fonctionnel
- Devra être capable d’accueillir plusieurs clients //Fonctionnel


#Problèmes rencontrés

- L'envoie de deux types d'objets différent sur un flux (comment séparer un String d'un ArrayList lors de la réception)
	Solution envisagée: envoyer chaque objet sur un OutputStream différent (un sur ObjectOutputStream et un sur ObjectStreamWriter) -> ne fonctionne pas	
