import { Injectable } from '@angular/core';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { HttpClient } from '@angular/common/http';
import { AuthResponseDto } from '../dto/auth-response-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "";
  private _roles: string[] = [];

  constructor(private http: HttpClient) {
    this._token = sessionStorage.getItem("token") ?? "";
    this.resetRoles();
  }

  public get token(): string {
    return this._token;
  }

  public auth(authRequest: AuthRequestDto): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post<AuthResponseDto>('/auth', authRequest.toJson()).subscribe({
        // next => si la réponse est OK
        next: resp => {
          if (resp.success == false) {
            reject();
            return;
          }

          this._token = resp.token;

          sessionStorage.setItem("token", this._token);
          this.resetRoles();

          resolve();
        },

        // error => si la réponse est KO (30X, 40X, 50X)
        error: err => reject(err)
      });
    })
  }

  public isLogged() {
    return !!this._token;
  }

  private resetRoles() {
    try {
      const payload = JSON.parse(atob(this._token.split('.')[1]));

      this._roles = payload.roles.split(',');
    }

    catch { }
  }

  public hasRole(role: string): boolean {
    role = `ROLE_${ role }`;

    return !!(this._roles.find(r => r == role));
  }
}
