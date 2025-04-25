import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import {provideNgxMask} from 'ngx-mask'
import { AuthInterceptor } from './services/auth-service/auth.interceptor.service';

export const appConfig: ApplicationConfig = {
  providers: 
  [provideZoneChangeDetection({ eventCoalescing: true }),
     provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideNgxMask({}),
    provideHttpClient(
      withInterceptors([AuthInterceptor])
    )]
};
