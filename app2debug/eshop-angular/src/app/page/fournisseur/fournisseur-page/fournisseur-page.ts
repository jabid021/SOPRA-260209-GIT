import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { FournisseurDto } from '../../../dto/fournisseur-dto';
import { AuthService } from '../../../service/auth-service';

@Component({
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './fournisseur-page.html',
  styleUrl: './fournisseur-page.css',
})
export class FournisseurPage implements OnInit {
  protected fournisseurs$!: Observable<FournisseurDto[]>;

  protected hasRoleAdmin: boolean = false;
  protected showForm: boolean = false;
  protected fournisseurForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected editingFournisseur!: FournisseurDto | null;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.fournisseurs$ = this.fournisseurService.findAll();
    this.hasRoleAdmin = this.authService.hasRole('ADMIN');

    this.nameCtrl = this.formBuilder.control('', Validators.required);

    this.fournisseurForm = this.formBuilder.group({
      name: this.nameCtrl
    });
  }

  public trackFournisseur(index: number, value: FournisseurDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingFournisseur) {
      this.fournisseurService.save(new FournisseurDto(this.editingFournisseur.id, this.nameCtrl.value));
    }

    else {
      this.fournisseurService.save(new FournisseurDto(0, this.nameCtrl.value));
    }

    this.editingFournisseur = null;
    this.fournisseurForm.reset();
  }

  public editer(fournisseur: FournisseurDto) {
    this.editingFournisseur = fournisseur;
    this.showForm = true;

    this.nameCtrl.setValue(fournisseur.nom);
  }

  public annulerEditer() {
    this.editingFournisseur = null;
    this.fournisseurForm.reset();
    this.showForm = false;
  }

  public supprimer(matiere: FournisseurDto) {
    this.fournisseurService.deleteById(matiere.id);
  }
}
