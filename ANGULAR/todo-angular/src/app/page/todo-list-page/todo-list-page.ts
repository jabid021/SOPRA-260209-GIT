import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Todo } from '../../model/todo';
import { TodoService } from '../../service/todo-service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-list-page',
  imports: [ CommonModule, FormsModule ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  // Injection de dépendance par méthode inject
  private titleService: Title = inject(Title);
  private todoService: TodoService = inject(TodoService);
  protected todos$!: Observable<Todo[]>;
  protected formTodo: Todo = { id: 0, title: "", completed: false, userId: 0};

  // Instructions exécutées juste à l'initialisation du composant
  ngOnInit(): void {
    this.titleService.setTitle("Liste des todos");

    // Charger l'observable de la liste des Todos
    this.todos$ = this.todoService.findAll();
  }

  public addTodo() {
    this.todoService.add(this.formTodo).subscribe();
  }
}
