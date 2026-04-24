// MARTIN

import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { CollectionService } from './collection-service';
import { Collection } from '../model/collection';

describe('CollectionService', () => {
  let service: CollectionService;
  let httpMock: HttpTestingController;

  const API = '/api/collection';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        CollectionService,
        provideHttpClient(),
        provideHttpClientTesting(),
      ],
    });
    service = TestBed.inject(CollectionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => httpMock.verify());

  // ── findAll ────────────────────────────────────────────────────────────

  it('should fetch all collections via GET', () => {
    const mockList: Collection[] = [
      { id: 1, nom: 'SF' },
      { id: 2, nom: 'Jeunesse' },
    ];

    service.findAll().subscribe(data => {
      expect(data.length).toBe(2);
      expect(data[0].nom).toBe('SF');
    });

    const req = httpMock.expectOne(API);
    expect(req.request.method).toBe('GET');
    req.flush(mockList);
  });

  // ── findById ───────────────────────────────────────────────────────────

  it('should fetch a single collection by id via GET', () => {
    const mock: Collection = { id: 1, nom: 'SF' };

    service.findById(1).subscribe(data => {
      expect(data.nom).toBe('SF');
    });

    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mock);
  });

  // ── add ────────────────────────────────────────────────────────────────

  it('should POST a new collection', () => {
    const payload = { nom: 'Thriller' } as Collection;
    const created: Collection = { id: 3, nom: 'Thriller' };

    service.add(payload).subscribe(data => {
      expect(data.id).toBe(3);
    });

    const req = httpMock.expectOne(API);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(payload);
    req.flush(created);
  });

  // ── update ─────────────────────────────────────────────────────────────

  it('should PUT an updated collection', () => {
    const payload = { nom: 'Science-Fiction' };
    const updated: Collection = { id: 1, nom: 'Science-Fiction' };

    service.update(1, payload).subscribe(data => {
      expect(data.nom).toBe('Science-Fiction');
    });

    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('PUT');
    req.flush(updated);
  });

  // ── deleteById ─────────────────────────────────────────────────────────

  it('should DELETE a collection by id', () => {
    service.deleteById(1).subscribe(res => {
      expect(res).toBeNull();
    });

    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null, { status: 204, statusText: 'No Content' });
  });
});
