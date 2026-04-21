import { Injectable } from '@angular/core';
import { Todo } from '../model/todo';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  constructor(private http: HttpClient) { }

  public findAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>("https://jsonplaceholder.typicode.com/todos");
  }

  public add(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>("https://jsonplaceholder.typicode.com/todos", todo);
  }
}
