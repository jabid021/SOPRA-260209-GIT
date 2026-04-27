import { Injectable } from '@angular/core';
import { ProduitDto } from '../dto/produit-dto';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProduitService {
  private apiUrl: string = '/produit';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<ProduitDto[]> {
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que ProduitDto[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<ProduitDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<ProduitDto> {
    return this.http.get<ProduitDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(produitDto: ProduitDto): void {
    const payload = produitDto.toJson();

    if (!produitDto.id) {
      this.http.post<ProduitDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<ProduitDto>(`${ this.apiUrl }/${ produitDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
