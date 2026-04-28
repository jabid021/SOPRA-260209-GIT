import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere-service';

@Component({
  selector: 'app-matiere-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  private titleService: Title = inject(Title);
  private matiereService: MatiereService = inject(MatiereService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected matieres$!: Observable<Matiere[]>;
  private refresh$: Subject<void> = new Subject<void>();
  // protected formMatiere: Matiere = { } as Matiere;

  protected formMatiere!: FormGroup;
  protected formLibelleCtrl!: FormControl;

  ngOnInit(): void {
    this.titleService.setTitle("Liste des matières");

    this.matieres$ = this.refresh$.pipe(
      startWith(0), // Initialisation => forcer le chargement une première fois
      switchMap(() => this.matiereService.findAll()) // Transformer au moment du next()
    );

    // Fabrication du formulaire avec le FormBuilder
    this.formLibelleCtrl = this.formBuilder.control("", Validators.required);

    this.formMatiere = this.formBuilder.group({
      // Description des contrôles du formulaire
      libelle: this.formLibelleCtrl
    });
  }

  private reload() {
    this.refresh$.next();

    // Sinon, rechargement de la page
    // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this.router.navigate([ '/matiere' ]);
    // });
  }

  public addMatiere() {
    const matiere: Matiere = {
      id: 0,
      libelle: this.formLibelleCtrl.value
    };

    this.matiereService.add(matiere).subscribe(() => this.reload());
  }

  public deleteMatiere(matiere: Matiere) {
    this.matiereService.deleteById(matiere.id).subscribe(() => this.reload());
  }
}
