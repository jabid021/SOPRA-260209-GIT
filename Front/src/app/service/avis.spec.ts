import { TestBed } from '@angular/core/testing';

import { Avis } from './avis';

describe('Avis', () => {
  let service: Avis;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Avis);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
