import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from './auth';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Auth);
  const token = authService.getToken();

  if (token) {
    const reqAvecToken = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token)
    });
    return next(reqAvecToken);
  }

  return next(req);
};