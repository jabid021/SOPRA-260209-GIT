import { Routes } from '@angular/router';
import { Auteur } from './composants/auteur/auteur';
import { Livre } from './composants/livre/livre';
import { Collection } from './composants/collection/collection';
import { Editeur } from './composants/editeur/editeur';
import { Avis } from './composants/avis/avis';

export const routes: Routes = [
  { path: 'auteurs', component: Auteur },
  { path: 'livres', component: Livre },
  { path: 'collections', component: Collection },
  { path: 'editeurs', component: Editeur },
  { path: 'avis', component: Avis },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];  