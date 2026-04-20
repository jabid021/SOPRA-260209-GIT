import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [ FormsModule ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected prenom: string = "Jérémy";
  protected couleur: string = "black";

  public resetPrenom() {
    this.prenom = "Nouveau";
  }

  public updatePrenom(event: any) {
    // console.log(event.target.value);
    this.prenom = event.target.value;
  }
}
