import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from '../../model/todo';

@Component({
  selector: 'ajc-todo-list',
  imports: [ CommonModule ],
  templateUrl: './ajc-todo-list.html',
  styleUrl: './ajc-todo-list.css',
})
export class AjcTodoList {
  @Input() public todos!: Array<Todo>;
  @Output() public deleted: EventEmitter<Todo> = new EventEmitter<Todo>();

  public todoById(index: number, todo: Todo) {
    return todo.id;
  }

  public onDelete(todo: Todo) {
    this.deleted.emit(todo);
  }
}
