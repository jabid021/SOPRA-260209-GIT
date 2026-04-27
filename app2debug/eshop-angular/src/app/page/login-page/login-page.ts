import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthRequestDto } from '../../dto/auth-request-dto';
import { AuthService } from '../../service/auth-service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  protected loginError: boolean = false;
  protected userForm!: FormGroup;
  protected usernameCtrl!: FormControl;
  protected passwordCtrl!: FormControl;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);

    this.userForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }

  public async connecter() {
    try {
      await this.authService.auth(new AuthRequestDto(this.usernameCtrl.value, this.passwordCtrl.value));

      this.router.navigate([ '/produit' ]);
    }

    // Si la connexion n'a pas pu se faire, affichage de l'erreur sur le template
    catch {
      this.loginError = true;
    }
  }
}
