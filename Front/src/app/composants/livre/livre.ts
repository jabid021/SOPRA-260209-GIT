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

  formLivre: Livre = {
    titre: '',
    resume: '',
    annee: ''
  };

  selectedId: number | null = null;

  constructor(private livreService: LivreService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadLivres();
  }

  loadLivres() {
    this.livreService.getAll().subscribe((data: Livre[]) => {
      console.log("DATA RECEIVED:", data);
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
      annee: ''
    };
    this.selectedId = null;
  }

}
