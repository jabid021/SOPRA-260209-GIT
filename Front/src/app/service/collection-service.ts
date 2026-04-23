// MARTIN

import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../model/collection';

@Injectable({ providedIn: 'root' })
export class CollectionService 
{
  private http: HttpClient = inject(HttpClient);

  public findAll(): Observable<Collection[]> 
  {
    return this.http.get<Collection[]>('/api/collection');
  }

  public add(collection: Collection): Observable<Collection> 
  {
    return this.http.post<Collection>('/api/collection', collection);
  }

  public deleteById(id: number): Observable<void> 
  {
    return this.http.delete<void>(`/api/collection/${id}`);
  }
}
