import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("Interception !!");

  const apiRequest = req.clone({
    url: "http://localhost:8080/api" + req.url
  });

  return next(apiRequest);
};
