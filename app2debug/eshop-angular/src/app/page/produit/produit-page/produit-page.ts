import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { ProduitDto } from '../../../dto/produit-dto';
import { ProduitService } from '../../../service/produit-service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FournisseurDto } from '../../../dto/fournisseur-dto';
import { FournisseurService } from '../../../service/fournisseur-service';

@Component({
  imports: [ RouterLink, ReactiveFormsModule ],
  templateUrl: './produit-page.html',
  styleUrl: './produit-page.css',
})
export class ProduitPage implements OnInit {
  protected produits$!: Observable<ProduitDto[]>;
  protected fournisseurs$!: Observable<FournisseurDto[]>;

  protected showForm: boolean = false;
  protected produitForm!: FormGroup;
  protected labelCtrl!: FormControl;
  protected priceCtrl!: FormControl;
  protected fournisseurCtrl!: FormControl;
  protected editingProduit!: ProduitDto | null;

  constructor(private produitService: ProduitService, private fournisseurService: FournisseurService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.produits$ = this.produitService.findAll();
    this.fournisseurs$ = this.fournisseurService.findAll();

    this.labelCtrl = this.formBuilder.control('', Validators.required);
    this.priceCtrl = this.formBuilder.control('', [ Validators.required, Validators.min(1) ]);
    this.fournisseurCtrl = this.formBuilder.control('', Validators.required);

    this.produitForm = this.formBuilder.group({
      label: this.labelCtrl,
      price: this.priceCtrl,
      fournisseur: this.fournisseurCtrl
    });
  }

  public trackProduit(index: number, value: ProduitDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingProduit) {
      this.produitService.save(new ProduitDto(this.editingProduit.id, this.labelCtrl.value, this.priceCtrl.value, this.fournisseurCtrl.value));
    }

    else {
      this.produitService.save(new ProduitDto(0, this.labelCtrl.value, this.priceCtrl.value, this.fournisseurCtrl.value));
    }

    this.showForm = false;
    this.editingProduit = null;
    this.produitForm.reset();
  }

  public editer(matiere: ProduitDto) {
    this.editingProduit = matiere;
    this.showForm = true;

    this.labelCtrl.setValue(matiere.nom);
    this.priceCtrl.setValue(matiere.prix);
    this.fournisseurCtrl.setValue(matiere.fournisseurId);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingProduit = null;
    this.produitForm.reset();
  }

  public supprimer(matiere: ProduitDto) {
    this.produitService.deleteById(matiere.id);
  }
}
