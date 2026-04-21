import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TodoService } from '../../service/todo-service';

@Component({
  selector: 'app-todo-list-page',
  imports: [],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  // Injection de dépendance par méthode inject
  private titleService: Title = inject(Title);
  private todoService: TodoService = inject(TodoService);

  // Instructions exécutées juste à l'initialisation du composant
  ngOnInit(): void {
    this.titleService.setTitle("Liste des todos");

    // Vérification
    this.todoService.findAll();
  }
}
