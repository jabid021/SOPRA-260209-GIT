import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home-page',
  imports: [],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {

  // Injection de dépendance par constructeur
  constructor(private titleService: Title) {
    this.titleService.setTitle("Accueil");
  }

}
