import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Avis } from '../model/Avis';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AvisService {
    private apiUrl = '/api/avis';

    constructor(private http: HttpClient) {}

    getAll(): Observable<Avis[]> {
      return this.http.get<Avis[]>(this.apiUrl);
    }

    getById(id: number): Observable<Avis> {
      return this.http.get<Avis>(`${this.apiUrl}/${id}`);
    }

    create(avis: Avis): Observable<Avis> {
      return this.http.post<Avis>(this.apiUrl, avis);
    }

    update(id: number, avis: Avis): Observable<Avis> {
      return this.http.post<Avis>(`${this.apiUrl}/${id}`, avis);
    }

    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
