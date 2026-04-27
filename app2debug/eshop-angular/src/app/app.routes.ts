import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/login-page/login-page';
import { ProduitPage } from './page/produit/produit-page/produit-page';
import { FournisseurPage } from './page/fournisseur/fournisseur-page/fournisseur-page';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [
    { path: '', component: HomePage, canActivate: [ authGuard ] },
    { path: 'home', component: HomePage, canActivate: [ authGuard ] },
    { path: 'login', component: LoginPage },
    { path: 'produit', component: HomePage, canActivate: [ authGuard ] },
    { path: 'fournisseur', component: FournisseurPage, canActivate: [ authGuard ] }
];
