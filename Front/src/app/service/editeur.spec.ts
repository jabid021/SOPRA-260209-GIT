import { TestBed } from '@angular/core/testing';

import { Editeur } from './editeur';

describe('Editeur', () => {
  let service: Editeur;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Editeur);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
