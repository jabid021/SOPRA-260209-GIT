import { Component,OnInit,ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LivreService } from '../../service/livre';
import { Livre } from '../../model/livre';

@Component({
  selector: 'app-livre',
  imports: [FormsModule, CommonModule],
  templateUrl: './livre.html',
  styleUrl: './livre.css',
})
export class LivreComponent implements OnInit {
  livres: Livre[] = [];
  showForm = false;
  isEditing = false;

  formLivre: Livre = {
    titre: '',
    resume: '',
    annee: '',
    auteur: '',
    editeur: '',
    collection: ''
  };

  selectedId: number | null = null;

  constructor(private livreService: LivreService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadLivres();
  }

  loadLivres() {
    this.livreService.getAll().subscribe((data: Livre[]) => {
      this.livres = data;
      this.cdr.detectChanges(); // Force le refresh de l'ui pour l'affichage des livres
    });
  }

  submit() {
    if (this.selectedId) {
      this.livreService.update(this.selectedId, this.formLivre).subscribe(() => {
        this.resetForm();
        this.loadLivres();
      });
    } else {
      this.livreService.create(this.formLivre).subscribe(() => {
        this.resetForm();
        this.loadLivres();
      });
    }
  }

  edit(livre: Livre) {
    this.formLivre = { ...livre };
    this.selectedId = livre.id!;
  }

  delete(id: number) {
    this.livreService.delete(id).subscribe(() => {
      this.loadLivres();
    });
  }

  resetForm() {
    this.formLivre = {
      titre: '',
      resume: '',
      annee: '',
      auteur: '',
      editeur: '',
      collection: ''
    };
    this.selectedId = null;
  }

  openAddForm() {
      this.showForm = true;
      this.isEditing = false;
      this.formLivre = { titre: '', resume: '', annee: '',  auteur: '', editeur: '', collection: '' };
    }

  openEditForm(livre: any) {
      this.showForm = true;
      this.isEditing = true;
      this.selectedId = livre.id;
      this.formLivre = { titre: livre.titre, resume: livre.resume, annee: livre.annee, auteur: livre.auteur, editeur: livre.editeur, collection: livre.collection};
  }

   cancel() {
      this.showForm = false;
    }
}
