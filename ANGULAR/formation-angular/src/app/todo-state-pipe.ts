import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'todoState',
})
export class TodoStatePipe implements PipeTransform {
  transform(value: string, ...args: unknown[]): string {
    return "Valeur = " + value;
  }
}
