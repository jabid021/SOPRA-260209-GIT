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

  public resetPrenom() {
    this.prenom = "Nouveau";
  }

  public updatePrenom(event: any) {
    // console.log(event.target.value);
    this.prenom = event.target.value;
  }
}
