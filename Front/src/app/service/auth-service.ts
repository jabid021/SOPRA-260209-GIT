// MARTIN

import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from '../dto/auth-request';
import { AuthResponse } from '../dto/auth-response';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private http: HttpClient = inject(HttpClient);

  // Chargé depuis sessionStorage pour survivre à un rechargement (F5)
  private _token: string = sessionStorage.getItem('token') ?? '';

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
    sessionStorage.setItem('token', value);
  }

  public isLogged(): boolean {
    return this._token.length > 0;
  }

  public auth(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/auth', authRequest);
  }

  public logout(): void {
    this._token = '';
    sessionStorage.removeItem('token');
  }
}
