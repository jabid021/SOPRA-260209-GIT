// MARTIN

import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../model/collection';

@Injectable({ providedIn: 'root' })
export class CollectionService {
  private http: HttpClient = inject(HttpClient);
  private readonly apiUrl = '/api/collection';

  public findAll(): Observable<Collection[]> {
    return this.http.get<Collection[]>(this.apiUrl);
  }

  public findById(id: number): Observable<Collection> {
    return this.http.get<Collection>(`${this.apiUrl}/${id}`);
  }

  public add(collection: Omit<Collection, 'id'>): Observable<Collection> {
    return this.http.post<Collection>(this.apiUrl, collection);
  }

  public update(id: number, collection: Partial<Collection>): Observable<Collection> {
    return this.http.put<Collection>(`${this.apiUrl}/${id}`, collection);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
