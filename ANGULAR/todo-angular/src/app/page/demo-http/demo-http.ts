import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Todo } from '../../model/todo';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-demo-http',
  imports: [ CommonModule ],
  templateUrl: './demo-http.html',
  styleUrl: './demo-http.css',
})
export class DemoHttp {
  // private todos!: Todo[];

  protected todos$!: Observable<Todo[]>;


  constructor(private http: HttpClient) {
    this.todos$ = this.http.get<Todo[]>("https://jsonplaceholder.typicode.com/todos");
  }

  // public findAll(): Todo[] {
  //   this.http.get<Todo[]>("https://jsonplaceholder.typicode.com/todos").subscribe(todos => {
  //     // this.todos = todos;

  //     for (let t of todos) {
  //       this.todos.push(t);
  //     }

  //   });

  //   return this.todos;
  // }

}
