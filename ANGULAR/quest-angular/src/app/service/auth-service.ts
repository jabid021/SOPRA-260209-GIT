import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../dto/auth-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "";

  public get token() : string {
    return this._token;
  }

  constructor(private http: HttpClient) { }

  public auth() {
    this.http.post<AuthResponse>("/auth", {
      "username": "jeremy",
      "password": "123456"
    }).subscribe(resp => {
      this._token = resp.token;
    });
  }

}
