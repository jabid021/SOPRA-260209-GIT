import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Livre } from '../model/livre';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
    private apiUrl = 'http://localhost:8080/api/livre';

    constructor(private http: HttpClient) {}

    getAll(): Observable<Livre[]> {
      return this.http.get<Livre[]>(this.apiUrl);
    }

    getById(id: number): Observable<Livre> {
      return this.http.get<Livre>(`${this.apiUrl}/${id}`);
    }

    create(livre: Livre): Observable<Livre> {
      return this.http.post<Livre>(this.apiUrl, livre);
    }

    update(id: number, livre: Livre): Observable<Livre> {
      return this.http.post<Livre>(`${this.apiUrl}/${id}`, livre);
    }

    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
