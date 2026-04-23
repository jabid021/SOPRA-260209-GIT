//MARTIN

import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../service/auth-service';
import { inject } from '@angular/core';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => 
  {
  const authService: AuthService = inject(AuthService);
  if (req.url.endsWith('/api/auth')) 
  {
    return next(req);
  }

  const jwtRequest = req.clone(
  {
    setHeaders: 
    {
      'Authorization': `Bearer ${ authService.token }`
    }
  });
  return next(jwtRequest);
};
