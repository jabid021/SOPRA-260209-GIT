import { HttpInterceptorFn } from '@angular/common/http';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  const jwtRequest = req.clone({
    setHeaders: {
      'Authorization': 'Bearer le_jeton_en_question'
    }
  });

  return next(jwtRequest);
};
