import { TestBed } from '@angular/core/testing';

import { Auteur } from './auteur';

describe('Auteur', () => {
  let service: Auteur;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Auteur);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
