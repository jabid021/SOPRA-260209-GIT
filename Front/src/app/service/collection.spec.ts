import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { CollectionService } from './collection';

describe('CollectionService', () => {
  let service: CollectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()],
    });
    service = TestBed.inject(CollectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
