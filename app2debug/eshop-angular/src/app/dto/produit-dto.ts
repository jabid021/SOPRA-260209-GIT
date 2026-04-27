export class ProduitDto {
    constructor(private _id: number, private _nom: string, private _prix: number, private _fournisseurId?: number, private _fournisseurNom?: string) { }

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get nom(): string {
        return this._nom;
    }

    public set nom(value: string) {
        this._nom = value;
    }

    public get prix(): number {
        return this._prix;
    }

    public set prix(value: number) {
        this._prix = value;
    }

    public get fournisseurId(): number | undefined {
        return this._fournisseurId;
    }

    public set fournisseurId(value: number) {
        this._fournisseurId = value;
    }

    public get fournisseurNom(): string | undefined {
        return this._fournisseurNom;
    }

    public set fournisseurNom(value: string) {
        this._fournisseurNom = value;
    }

    public toJson(): any {
        return {
            nom: this.nom,
            prix: this.prix,
            fournisseurId: this.fournisseurId
        };
    }
}
