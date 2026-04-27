import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { FournisseurDto } from '../dto/fournisseur-dto';

@Injectable({
  providedIn: 'root',
})
export class FournisseurService {
  private apiUrl: string = '/fournisseur';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<FournisseurDto[]> {
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      switchMap(() => {
        return this.http.get<FournisseurDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<FournisseurDto> {
    return this.http.get<FournisseurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(fournisseurDto: FournisseurDto): void {
    const payload = fournisseurDto.toJson();

    if (!fournisseurDto.id) {
      this.http.post<FournisseurDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<FournisseurDto>(`${ this.apiUrl }/${ fournisseurDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
