import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../dto/auth-response';
import { AuthRequest } from '../dto/auth-request';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "";

  public get token() : string {
    return this._token;
  }

  constructor(private http: HttpClient) { }

  public auth(authRequest: AuthRequest) {
    this.http.post<AuthResponse>("/auth", authRequest).subscribe(resp => {
      this._token = resp.token;
    });
  }

}
