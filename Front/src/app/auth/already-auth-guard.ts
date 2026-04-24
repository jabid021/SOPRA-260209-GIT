import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from './auth';

export const alreadyAuthGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth);
  const router = inject(Router);

  console.log('already auth guard appelé, isLoggedIn:', authService.isLoggedIn());

  if (authService.isLoggedIn()) {
    router.navigate(['/auteurs']);
    return false;
  } else {
    return true;
  }
};