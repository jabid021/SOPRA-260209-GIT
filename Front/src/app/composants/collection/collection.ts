import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CollectionService } from '../../service/collection';
import { Collection } from '../../model/collection';

@Component({
  selector: 'app-collection',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './collection.html',
  styleUrl: './collection.css',
})
export class CollectionComponent implements OnInit {
  collections: Collection[] = [];
  showForm = false;
  isEditing = false;

  formCollection: Collection = { nom: '' };
  selectedId: number | null = null;

  constructor(private collectionService: CollectionService) {}

  ngOnInit() {
    this.loadCollections();
  }

  loadCollections() {
    this.collectionService.getAll().subscribe(data => {
      this.collections = data;
    });
  }

  openAddForm() {
    this.showForm = true;
    this.isEditing = false;
    this.formCollection = { nom: '' };
  }

  openEditForm(collection: Collection) {
    this.showForm = true;
    this.isEditing = true;
    this.selectedId = collection.id!;
    this.formCollection = { nom: collection.nom };
  }

  submit() {
    if (this.isEditing && this.selectedId !== null) {
      this.collectionService.update(this.selectedId, this.formCollection).subscribe(() => {
        this.loadCollections();
        this.showForm = false;
      });
    } else {
      this.collectionService.create(this.formCollection).subscribe(() => {
        this.loadCollections();
        this.showForm = false;
      });
    }
  }

  delete(id: number) {
    if (confirm('Supprimer cette collection ?')) {
      this.collectionService.delete(id).subscribe(() => {
        this.loadCollections();
      });
    }
  }

  cancel() {
    this.showForm = false;
  }
}
