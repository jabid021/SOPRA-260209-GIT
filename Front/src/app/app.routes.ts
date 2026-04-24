import { Routes } from '@angular/router';

import { Login } from './auth/login/login';

import { Auteur } from './composants/auteur/auteur';
import { Livre } from './composants/livre/livre';
import { Collection } from './composants/collection/collection';
import { Editeur } from './composants/editeur/editeur';
import { Avis } from './composants/avis/avis';

import { authGuard } from './auth/auth-guard';
import { alreadyAuthGuard } from './auth/already-auth-guard';

export const routes: Routes = [
  { path: 'login', component: Login, canActivate: [alreadyAuthGuard] },
  { path: 'auteurs', component: Auteur, canActivate: [authGuard] },
  { path: 'livres', component: Livre, canActivate: [authGuard] },
  { path: 'collections', component: Collection, canActivate: [authGuard] },
  { path: 'editeurs', component: Editeur, canActivate: [authGuard] },
  { path: 'avis', component: Avis, canActivate: [authGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];