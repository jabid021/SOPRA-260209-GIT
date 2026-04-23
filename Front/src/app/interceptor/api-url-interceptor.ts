// MARTIN

import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => 
  {
  const modifiedReq = req.clone(
  {
    url: 'http://localhost:8080' + req.url
  });

  return next(modifiedReq);
};
