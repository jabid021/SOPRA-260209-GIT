import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere-service';

@Component({
  selector: 'app-matiere-page',
  imports: [ CommonModule, FormsModule ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  private titleService: Title = inject(Title);
  private matiereService: MatiereService = inject(MatiereService);

  protected matieres$!: Observable<Matiere[]>;
  private refresh$: Subject<void> = new Subject<void>();
  protected formMatiere: Matiere = { } as Matiere;

  ngOnInit(): void {
    this.titleService.setTitle("Liste des matières");

    this.matieres$ = this.refresh$.pipe(
      startWith(0), // Initialisation => forcer le chargement une première fois
      switchMap(() => this.matiereService.findAll()) // Transformer au moment du next()
    );
  }

  private reload() {
    this.refresh$.next();

    // Sinon, rechargement de la page
    // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this.router.navigate([ '/matiere' ]);
    // });
  }

  public addMatiere() {
    this.matiereService.add(this.formMatiere).subscribe(() => this.reload());
  }

  public deleteMatiere(matiere: Matiere) {
    this.matiereService.deleteById(matiere.id).subscribe(() => this.reload());
  }
}
