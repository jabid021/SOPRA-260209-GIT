import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthRequest } from '../../dto/auth-request';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);

  // protected formAuth: AuthRequest = { } as AuthRequest;
  // protected formAuth: AuthRequest = { username: "", password: "" };
  protected formAuth!: FormGroup;
  protected formUsernameCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  ngOnInit(): void {
    const customValidator = (arg: string): ValidatorFn => {
      return (control: AbstractControl): ValidationErrors | null => {
        // Si erreur
        const isValid = control.value === arg;

        if (!isValid) {
          return { compare: true };
        }

        // Si pas d'erreur
        return null;
      };
    };

    const customValidatorV2 = (control: AbstractControl): ValidationErrors | null => {
      // Si erreur
      const isValid = /^[a-z]+$/.test(control.value);

      if (!isValid) {
        return { minusculeV2: true };
      }

      // Si pas d'erreur
      return null;
    };

    this.formUsernameCtrl = this.formBuilder.control("", [ Validators.required, customValidator("test"), customValidatorV2 ]);
    this.formPasswordCtrl = this.formBuilder.control("", Validators.required);

    this.formAuth = this.formBuilder.group({
      username: this.formUsernameCtrl,
      password: this.formPasswordCtrl
    });
  }

  public connexion() {
    const authRequest: AuthRequest = {
      username: this.formUsernameCtrl.value,
      password: this.formPasswordCtrl.value
    };

    this.authService.auth(authRequest).subscribe(resp => {
      if (resp.success) {
        this.authService.token = resp.token;
        this.router.navigate([ '/matiere' ]);
      }
    });
  }
}
