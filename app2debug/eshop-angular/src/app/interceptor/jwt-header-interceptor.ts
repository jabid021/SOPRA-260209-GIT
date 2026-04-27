import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth-service';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  // Pas de jeton, donc pas besoin de l'injecter ...
  if (!authService.token) {
    return next(req);
  }

  // API de connexion, donc pas besoin du jeton ...
  if (req.url.endsWith('/api/auth')) {
    return next(req);
  }

  // Dans les autres cas, on injecte le JWT
  const authReq = req.clone({
    setHeaders: {
      'Authorisation': `Bearer ${ authService.token }`
    }
  });

  return next(authReq);
};
