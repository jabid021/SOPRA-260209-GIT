import { Component, Input } from '@angular/core';
import { Todo } from '../../model/todo';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'ajc-todo-list',
  imports: [ CommonModule ],
  templateUrl: './ajc-todo-list.html',
  styleUrl: './ajc-todo-list.css',
})
export class AjcTodoList {

  @Input() public todos!: Array<Todo>;

  public todoById(index: number, todo: Todo) {
    return todo.id;
  }

}
