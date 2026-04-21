import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    // Fournisseur des services de gestion du navigateur
    provideBrowserGlobalErrorListeners(),

    // Fournisseur du service Router
    provideRouter(routes),

    // Fournisseur du service HTTP
    provideHttpClient()
  ]
};
