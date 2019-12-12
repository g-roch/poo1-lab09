# Échecs
## Mouvement
 - Aucune pièces ne peut pas sauter (Sauf le cavalier)
 - Le déplacement d'une pièce est interdite, si ce mouvement metterais le roi en échecs
### Roi
 - 1 case dans n'importe quel direction
 - (Voir Mouvement.2) Ne peut pas aller sur un case qui est attaquée
### Roque
 - Roi 2 case vers la tours
 - Tour se place de l'autre côté du roi
 - Le roi et la tour ne doivent jamais avoir bougé
 - Le roi n'est pas en échecs
 - Les cases traversée par le roi n'est pas en échecs
### Tour
 - Déplacement horizontal et vertical.
### Fou
 - Déplacement en diagonale
### Dame
 - addition d'une tour et d'un fou
### Cavalier
 - 2 case dans une direction puis 1 case perpendiculairement à cette direction
### Pion
 - 1 case vers l'avant
 - 1 ou 2 case vers l'avant lors de son premier mouvement

## Prise
### Roi Tour Fou Dame Cavalier
 - La piece adverse doit se trouver sur un case accessible par la piece (Si la piece adverse n'existait pas)
 - La piece se place à la place de la piece adverse
### Pion (Prise standard)
 - Peut prendre uniquement les pieces se trouvant en digonale avant d'une case
 - La piece se place à la place de la piece adverse
### Pion (Prise en passant)
 - Peut prendre un pion à côté de lui, si ce dernire vien d'avancer de 2 cases
 - La piece se place en digonale dériere le pion.

## Promotion du Pion
 - Lorsque le pion arrive sur la dernière ligne, il se transforme au choix en
   - Dame
   - Tour
   - Fou
   - Cavalier

## Nulle
### Pat
 - Le joueur au trait n'a aucun mouvement possible
### Manque de piece
 - Roi vs roi
 - Roi vs fou roi
 - Roi vs cavalier roi
 - Roi fou vs fou roi (les deux fous de meme couleurs
### 50 coups (manuel)
 - lorsque 50 coups on été jouer par chaque joueur sans prise ni déplacement de pion
### Répétition (manuel)
 - 3× le même échiquier, avec le même joueur au trait, les mêmes coups possible (Prise en passant et Roque)

## Implentation
 - Tour & Roi indication s'ils on bougé (roque)
 - Pion - Acces au dernier coup de l'adversaire (prise en passant)
 - Nombre de coups par chaque joueur depuis la dernière prise / déplacement de pion
 - Count nb échiquier de l'historique (différence sur possibilité de Roque / Prise en passant)
