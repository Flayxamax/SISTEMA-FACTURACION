import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { toast } from 'ngx-sonner';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 0) {
        toast.error('Could not connect to the server');
      } else if (error.status === 400) {
        toast.error('Bad Request');
      } else if (error.status === 401) {
        toast.error('Unauthorized');
      } else if (error.status === 403) {
        toast.error('Forbidden');
      } else if (error.status === 404) {
        toast.error('Not Found');
      } else if (error.status === 500) {
        toast.error('Internal Server Error');
      }
      return throwError(() => error.error.message);
    })
  );
};
