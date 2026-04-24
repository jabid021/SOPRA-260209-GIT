import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Editeur } from '../model/editeur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
    private apiUrl = '/api/editeur';

    constructor(private http: HttpClient) {}

    getAll(): Observable<Editeur[]> {
      return this.http.get<Editeur[]>(this.apiUrl);
    }

    getById(id: number): Observable<Editeur> {
      return this.http.get<Editeur>(`${this.apiUrl}/${id}`);
    }

    create(editeur: Editeur): Observable<Editeur> {
      return this.http.post<Editeur>(this.apiUrl, editeur);
    }

    update(id: number, editeur: Editeur): Observable<Editeur> {
      return this.http.post<Editeur>(`${this.apiUrl}/${id}`, editeur);
    }

    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
