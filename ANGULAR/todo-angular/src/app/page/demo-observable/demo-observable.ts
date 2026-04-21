import { Component } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-demo-observable',
  imports: [],
  templateUrl: './demo-observable.html',
  styleUrl: './demo-observable.css',
})
export class DemoObservable {

  constructor() {
    let prom = new Promise((resolve, error) => {
      resolve(1);
    });

    prom.then(value => {
      console.log(value);
    });

    let obs = new Observable<number>(observer => {
      setTimeout(() => observer.next(1), 1000);
      setTimeout(() => observer.next(2), 2000);
      setTimeout(() => observer.next(3), 3000);
      setTimeout(() => observer.next(4), 4000);
      setTimeout(() => observer.complete(), 5000);
    });

    obs.subscribe(value => {
      console.log(value);
    });

    console.log("QUELQUE CHOSE ICI");
  }
}
