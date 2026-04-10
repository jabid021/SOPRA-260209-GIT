import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MatiereDto } from '../../../dto/matiere-dto';
import { MatiereService } from '../../../service/matiere-service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-matiere-page',
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  protected matieres$!: Observable<MatiereDto[]>;
  protected matiereForm!: FormGroup;
  protected labelCtrl!: FormControl;
  protected editingMatiere!: MatiereDto | null;

  constructor(private matiereService: MatiereService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.matieres$ = this.matiereService.findAll();

    this.labelCtrl = this.formBuilder.control('', Validators.required);

    this.matiereForm = this.formBuilder.group({
      label: this.labelCtrl
    });
  }

  public trackMatiere(index: number, value: MatiereDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingMatiere) {
      this.matiereService.save(new MatiereDto(this.editingMatiere.id, this.labelCtrl.value));
    }

    else {
      this.matiereService.save(new MatiereDto(0, this.labelCtrl.value));
    }

    this.editingMatiere = null;
    this.labelCtrl.setValue("");
  }

  public editer(matiere: MatiereDto) {
    this.editingMatiere = matiere;
    this.labelCtrl.setValue(matiere.libelle);
  }

  public supprimer(matiere: MatiereDto) {
    this.matiereService.deleteById(matiere.id);
  }
}
