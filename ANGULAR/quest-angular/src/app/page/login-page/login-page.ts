import { Component, inject } from '@angular/core';
import { AuthRequest } from '../../dto/auth-request';
import { AuthService } from '../../service/auth-service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  imports: [ FormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  // protected formAuth: AuthRequest = { } as AuthRequest;
  protected formAuth: AuthRequest = { username: "", password: "" };

  public connexion() {
    this.authService.auth(this.formAuth).subscribe(resp => {
      if (resp.success) {
        this.authService.token = resp.token;
        this.router.navigate([ '/matiere' ]);
      }
    });
  }
}
