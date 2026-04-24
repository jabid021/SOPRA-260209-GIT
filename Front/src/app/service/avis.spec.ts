import { TestBed } from '@angular/core/testing';

import { AvisService } from './avis';

describe('Avis', () => {
  let service: AvisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
