import { Component, Input } from '@angular/core';

@Component({
  selector: 'ajc-bold',
  imports: [ ],
  templateUrl: './ajc-bold.html',
  styleUrl: './ajc-bold.css',
})
export class AjcBold {
  // @Input('titre') public titre: string = "";
  @Input() public titre: string = "";
}
