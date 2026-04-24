import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe, NgIf, NgFor } from '@angular/common';
import { AvisService } from '../../service/avis';
import { Avis } from '../../model/avis';

@Component({
  selector: 'app-avis',
  imports: [FormsModule, DatePipe, NgIf, NgFor],
  templateUrl: './avis.html',
  styleUrl: './avis.css',
})

export class AvisComponent implements OnInit{
  avisList: AvisModel[] = [];
    livres: { id: number; titre: string }[] = []; // à adapter selon votre modèle Livre

    showForm = false;
    isEditing = false;

    formData: AvisModel = {
      note: 0,
      livreId: 0,
      date: '',
      commentaire: ''
    };

    private editingId: number | null = null;
    constructor (private avisService: AvisService, private cdr : ChangeDetectorRef) {}
    ngOnInit(): void {
      this.loadAvis;
    }

  loadAvis(): void {
      this.editeurService.getAll().subscribe((data: Avis[]) => {
            this.avis = data;
            this.cdr.detectChanges();
          });
    }

    openAddForm(): void {
      this.isEditing = false;
      this.editingId = null;
      this.formData = { note: 0, livreId: 0, date: '', commentaire: '' };
      this.showForm = true;
    }

    openEditForm(avis: AvisModel): void {
      this.isEditing = true;
      this.editingId = avis.id ?? null;
      this.formData = { ...avis };
      this.showForm = true;
    }

    submit(): void {
      if (this.isEditing && this.editingId !== null) {
        // TODO: appeler votre service pour modifier
        const index = this.avisList.findIndex(a => a.id === this.editingId);
        if (index !== -1) this.avisList[index] = { ...this.formData, id: this.editingId };
      } else {
        // TODO: appeler votre service pour ajouter
        this.avisList.push({ ...this.formData, id: Date.now() });
      }
      this.cancel();
    }

    delete(id: number): void {
      // TODO: appeler votre service pour supprimer
      this.avisList = this.avisList.filter(a => a.id !== id);
    }

    cancel(): void {
      this.showForm = false;
      this.isEditing = false;
      this.editingId = null;
      this.formData = { note: 0, livreId: 0, date: '', commentaire: '' };
    }
}
