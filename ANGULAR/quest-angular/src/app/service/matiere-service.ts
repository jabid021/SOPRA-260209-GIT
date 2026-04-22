import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Matiere } from '../model/matiere';

@Injectable({
  providedIn: 'root',
})
export class MatiereService {
  constructor(private http: HttpClient) { }

  public findAll(): Observable<Matiere[]> {
    return this.http.get<Matiere[]>("/matiere");
  }

  public add(matiere: Matiere): Observable<Matiere> {
    return this.http.post<Matiere>("/matiere", matiere);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`/matiere/${ id }`);
  }
}
