
class Personne {

    constructor(private _nom: string, private _prenom?: string) { }

    public get nom(): string {
        return this._nom;
    }

    public set nom(value : string) {
        this._nom = value;
    }

}

let personne: Personne = new Personne("Le nom");

console.log(personne.nom);

personne.nom = "Nouveau nom";

console.log(personne.nom);
