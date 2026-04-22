import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { MatierePage } from './page/matiere-page/matiere-page';
import { LoginPage } from './page/login-page/login-page';

export const routes: Routes = [
    { path: 'home', component: HomePage },
    { path: 'matiere', component: MatierePage },
    { path: 'login', component: LoginPage },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
