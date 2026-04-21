import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { TodoListPage } from './page/todo-list-page/todo-list-page';

export const routes: Routes = [
    { path: 'home', component: HomePage },
    { path: 'todo', component: TodoListPage },

    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
