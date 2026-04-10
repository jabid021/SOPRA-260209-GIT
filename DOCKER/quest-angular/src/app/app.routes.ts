import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { MatierePage } from './page/matiere/matiere-page/matiere-page';

export const routes: Routes = [
    { path: '', component: HomePage },
    { path: 'home', component: HomePage },
    { path: 'matiere', component: MatierePage }
];
