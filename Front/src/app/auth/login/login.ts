import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  username = '';
  password = '';
  erreur = '';

  constructor(private authService: Auth, private router: Router) {}

  onLogin() {
  this.authService.login(this.username, this.password).subscribe({
    next: (response) => {
      if (response.token && response.token !== '') {
        this.authService.saveToken(response.token);
        this.router.navigate(['/auteurs']);
      } else {
        this.erreur = 'Identifiants incorrects';
      }
    },
    error: () => {
      this.erreur = 'Identifiants incorrects';
    }
  });
}
}