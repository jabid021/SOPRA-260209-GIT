import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from '../dto/auth-request';
import { AuthResponse } from '../dto/auth-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = sessionStorage.getItem("token") ?? "";

  public get token() : string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
    sessionStorage.setItem("token", value);
  }

  constructor(private http: HttpClient) { }

  public auth(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>("/auth", authRequest);
  }

  public isLogged(): boolean {
    // return this._token != "";
    return !!this._token;
  }
}
