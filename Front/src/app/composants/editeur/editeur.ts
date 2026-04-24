import { Component,OnInit,ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EditeurService } from '../../service/editeur';
import { Editeur } from '../../model/editeur';

@Component({
  selector: 'app-editeur',
  imports: [FormsModule, CommonModule],
  templateUrl: './editeur.html',
  styleUrl: './editeur.css',
})

export class EditeurComponent implements OnInit {

  editeurs: Editeur[] = [];

  formEditeur: Editeur = {
    nom: '',
    pays: ''
  };

  selectedId: number | null = null;

  constructor (private editeurService: EditeurService, private cdr : ChangeDetectorRef) {}

  ngOnIniti(): void{
    this.loadEditeurs();
  }

  loadEditeurs() {
    this.editeurService.getAll().subscribe((data: Editeur[]) => {
      console.log("DATA RECEIVED:", data);
      this.editeurs = data;
      this.cdr.detectChanges();
    });
  }

  submit() {
    if (this.selectedId) {
      this.editeurService.update(this.selectedId, this.formEditeur).subscribe(() => {
        this.resetForm();
        this.loadEditeurs();
      });
    }
    else {
      this.editeurService.create(this.formEditeur).subscribe(() => {
        this.resetForm();
        this.loadEditeurs();
      });
    }
  }

  edit(editeur: Editeur) {
      this.formEditeur = { ...editeur };
      this.selectedId = editeur.id!;
    }

    delete(id: number) {
      this.editeurService.delete(id).subscribe(() => {
        this.loadEditeurs();
      });
    }

    resetForm() {
      this.formEditeur = {
        nom: '',
        pays: '',
      };
      this.selectedId = null;
    }
}
