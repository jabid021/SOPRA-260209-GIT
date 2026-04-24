import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuteurService } from '../../service/auteur';
import { Auteur } from '../../model/auteur';

@Component({
  selector: 'app-auteur',
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './auteur.html',
  styleUrl: './auteur.css'
})
export class AuteurComponent implements OnInit {
  auteurs: any[] = [];
  showForm = false;
  isEditing = false;

  formData = { nom: '', prenom: '', nationalite: '' };
  selectedId: number | null = null;

  constructor(private auteurService: AuteurService) {}

  ngOnInit() {
    this.loadAuteurs();
  }

  loadAuteurs() {
    this.auteurService.getAll().subscribe(data => {
      this.auteurs = data;
    });
  }

  openAddForm() {
    this.showForm = true;
    this.isEditing = false;
    this.formData = { nom: '', prenom: '', nationalite: '' };
  }

  openEditForm(auteur: any) {
    this.showForm = true;
    this.isEditing = true;
    this.selectedId = auteur.id;
    this.formData = { nom: auteur.nom, prenom: auteur.prenom, nationalite: auteur.nationalite };
  }

  submit() {
    if (this.isEditing && this.selectedId !== null) {
      this.auteurService.update(this.selectedId, this.formData).subscribe(() => {
        this.loadAuteurs();
        this.showForm = false;
      });
    } else {
      this.auteurService.create(this.formData).subscribe(() => {
        this.loadAuteurs();
        this.showForm = false;
      });
    }
  }

  delete(id: number) {
    if (confirm('Supprimer cet auteur ?')) {
      this.auteurService.delete(id).subscribe(() => {
        this.loadAuteurs();
      });
    }
  }

  cancel() {
    this.showForm = false;
  }
}