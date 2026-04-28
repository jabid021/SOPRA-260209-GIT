import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { MatierePage } from './page/matiere-page/matiere-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [
    { path: 'home', component: HomePage, canActivate: [ authGuard ] },
    { path: 'matiere', component: MatierePage, canActivate: [ authGuard ] },

    { path: 'login', component: LoginPage },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
