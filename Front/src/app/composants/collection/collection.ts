// MARTIN

import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Collection } from '../model/collection';
import { CollectionService } from '../service/collection-service';

@Component({
  selector: 'app-collection',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './collection.html',
  styleUrl: './collection.css',
})
export class CollectionComponent implements OnInit {

  private collectionService = inject(CollectionService);

  collections: Collection[] = [];
  errorMessage: string = '';
  successMessage: string = '';

  // Formulaire d'ajout
  newNom: string = '';
  isAdding: boolean = false;

  // Édition inline
  editingId: number | null = null;
  editingNom: string = '';

  ngOnInit(): void {
    this.loadCollections();
  }

  loadCollections(): void {
    this.collectionService.findAll().subscribe({
      next: (data) => {
        this.collections = data;
        this.errorMessage = '';
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les collections.';
      }
    });
  }

  // ── Ajout ────────────────────────────────────────────────────────────────

  openAddForm(): void {
    this.isAdding = true;
    this.newNom = '';
    this.clearMessages();
  }

  cancelAdd(): void {
    this.isAdding = false;
    this.newNom = '';
  }

  submitAdd(): void {
    const nom = this.newNom.trim();
    if (!nom) return;

    this.collectionService.add({ nom }).subscribe({
      next: (created) => {
        this.collections.push(created);
        this.isAdding = false;
        this.newNom = '';
        this.showSuccess('Collection ajoutée avec succès.');
      },
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = 'Une collection avec ce nom existe déjà.';
        } else {
          this.errorMessage = "Erreur lors de l'ajout.";
        }
      }
    });
  }

  // ── Édition ──────────────────────────────────────────────────────────────

  startEdit(collection: Collection): void {
    this.editingId = collection.id;
    this.editingNom = collection.nom;
    this.clearMessages();
  }

  cancelEdit(): void {
    this.editingId = null;
    this.editingNom = '';
  }

  submitEdit(collection: Collection): void {
    const nom = this.editingNom.trim();
    if (!nom) return;

    this.collectionService.update(collection.id, { nom }).subscribe({
      next: (updated) => {
        const index = this.collections.findIndex(c => c.id === updated.id);
        if (index !== -1) this.collections[index] = updated;
        this.editingId = null;
        this.showSuccess('Collection modifiée avec succès.');
      },
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = 'Ce nom est déjà utilisé par une autre collection.';
        } else {
          this.errorMessage = 'Erreur lors de la modification.';
        }
      }
    });
  }

  // ── Suppression ───────────────────────────────────────────────────────────

  deleteCollection(collection: Collection): void {
    if (!confirm(`Supprimer la collection « ${collection.nom} » ?`)) return;

    this.collectionService.deleteById(collection.id).subscribe({
      next: () => {
        this.collections = this.collections.filter(c => c.id !== collection.id);
        this.showSuccess('Collection supprimée.');
      },
      error: () => {
        this.errorMessage = 'Erreur lors de la suppression.';
      }
    });
  }

  // ── Helpers ───────────────────────────────────────────────────────────────

  private showSuccess(msg: string): void {
    this.successMessage = msg;
    this.errorMessage = '';
    setTimeout(() => (this.successMessage = ''), 3000);
  }

  private clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }
}
