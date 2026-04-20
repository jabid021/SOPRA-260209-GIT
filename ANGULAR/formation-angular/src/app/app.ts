import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Todo } from './model/todo';

@Component({
  selector: 'app-root',
  imports: [ FormsModule, CommonModule ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected prenom: string = "Jérémy";
  protected couleur: string = "black";
  protected formTodo: Todo = new Todo(1, "Cours Angular", false);
  protected todos: Array<Todo> = new Array<Todo>(this.formTodo, this.formTodo);

  public resetPrenom() {
    this.prenom = "Nouveau";
  }

  public updatePrenom(event: any) {
    // console.log(event.target.value);
    this.prenom = event.target.value;
  }

  public todoById(index: number, todo: Todo) {
    return todo.id;
  }

  public addTodo() {
    // this.todos.push(this.formTodo);
    // this.formTodo = new Todo(0, "", false);

    this.todos.push(new Todo(this.formTodo.id, this.formTodo.title, this.formTodo.completed, this.formTodo.userId));
  }
}
