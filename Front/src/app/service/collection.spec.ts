// MARTIN

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { CollectionComponent } from './collection';

describe('CollectionComponent', () => {
  let component: CollectionComponent;
  let fixture: ComponentFixture<CollectionComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionComponent],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(CollectionComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => httpMock.verify());

  // ── Initialisation ─────────────────────────────────────────────────────

  it('should create', () => {
    const req = httpMock.expectOne('/api/collection');
    req.flush([]);
    expect(component).toBeTruthy();
  });

  it('should load collections on init', () => {
    const mockCollections = [
      { id: 1, nom: 'SF' },
      { id: 2, nom: 'Jeunesse' },
    ];
    const req = httpMock.expectOne('/api/collection');
    req.flush(mockCollections);
    fixture.detectChanges();

    expect(component.collections.length).toBe(2);
    expect(component.collections[0].nom).toBe('SF');
  });

  it('should show an error message when loadCollections fails', () => {
    const req = httpMock.expectOne('/api/collection');
    req.flush('Erreur serveur', { status: 500, statusText: 'Internal Server Error' });
    fixture.detectChanges();

    expect(component.errorMessage).toBeTruthy();
  });

  // ── Ajout ──────────────────────────────────────────────────────────────

  it('should open add form', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    expect(component.isAdding).toBeTrue();
  });

  it('should add a collection and push it to the list', () => {
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    component.openAddForm();
    component.newNom = 'Thriller';
    component.submitAdd();

    const req = httpMock.expectOne({ method: 'POST', url: '/api/collection' });
    req.flush({ id: 3, nom: 'Thriller' });

    expect(component.collections.length).toBe(2);
    expect(component.collections[1].nom).toBe('Thriller');
    expect(component.isAdding).toBeFalse();
  });

  it('should show a 409 error message when name already exists', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    component.newNom = 'SF';
    component.submitAdd();

    const req = httpMock.expectOne({ method: 'POST', url: '/api/collection' });
    req.flush({ message: 'Conflict' }, { status: 409, statusText: 'Conflict' });

    expect(component.errorMessage).toContain('existe déjà');
  });

  // ── Édition ────────────────────────────────────────────────────────────

  it('should start edit mode for a collection', () => {
    httpMock.expectOne('/api/collection').flush([]);
    const collection = { id: 1, nom: 'SF' };
    component.startEdit(collection);

    expect(component.editingId).toBe(1);
    expect(component.editingNom).toBe('SF');
  });

  it('should update a collection', () => {
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    const collection = component.collections[0];
    component.startEdit(collection);
    component.editingNom = 'Science-Fiction';
    component.submitEdit(collection);

    const req = httpMock.expectOne({ method: 'PUT', url: '/api/collection/1' });
    req.flush({ id: 1, nom: 'Science-Fiction' });

    expect(component.collections[0].nom).toBe('Science-Fiction');
    expect(component.editingId).toBeNull();
  });

  // ── Suppression ────────────────────────────────────────────────────────

  it('should delete a collection from the list', () => {
    spyOn(window, 'confirm').and.returnValue(true);
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    component.deleteCollection(component.collections[0]);

    const req = httpMock.expectOne({ method: 'DELETE', url: '/api/collection/1' });
    req.flush(null, { status: 204, statusText: 'No Content' });

    expect(component.collections.length).toBe(0);
  });
});
