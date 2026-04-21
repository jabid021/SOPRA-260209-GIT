import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { TodoListPage } from './page/todo-list-page/todo-list-page';
import { DemoObservable } from './page/demo-observable/demo-observable';
import { DemoHttp } from './page/demo-http/demo-http';

export const routes: Routes = [
    { path: 'home', component: HomePage },
    { path: 'todo', component: TodoListPage },
    { path: 'obs', component: DemoObservable },
    { path: 'http', component: DemoHttp },

    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
