import { Injectable } from '@angular/core';
import { Todo } from '../model/todo';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private todos: Array<Todo> = new Array<Todo>();

  public findAll(): Todo[] {
    return this.todos;
  }

  public add(todo: Todo) {
    this.todos.push(todo);
  }
}
