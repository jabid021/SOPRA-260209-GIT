import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuteurService {
  private apiUrl = '/api/auteur';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.apiUrl);
  }

  getById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  create(auteur: any) {
    return this.http.post<any>(this.apiUrl, auteur);
  }

  update(id: number, auteur: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, auteur);
  }

  delete(id: number) {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
