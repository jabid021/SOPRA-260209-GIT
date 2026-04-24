import { Routes } from '@angular/router';

import { Login } from './auth/login/login';

import { AuteurComponent } from './composants/auteur/auteur';
import { LivreComponent } from './composants/livre/livre';
import { CollectionComponent } from './composants/collection/collection';
import { EditeurComponent } from './composants/editeur/editeur';
import { AvisComponent } from './composants/avis/avis';

import { authGuard } from './auth/auth-guard';
import { alreadyAuthGuard } from './auth/already-auth-guard';

export const routes: Routes = [
  { path: 'login', component: Login, canActivate: [alreadyAuthGuard] },
  { path: 'auteurs', component: AuteurComponent, canActivate: [authGuard] },
  { path: 'livres', component: LivreComponent, canActivate: [authGuard] },
  { path: 'collections', component: CollectionComponent, canActivate: [authGuard] },
  { path: 'editeurs', component: EditeurComponent, canActivate: [authGuard] },
  { path: 'avis', component: AvisComponent, canActivate: [authGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
