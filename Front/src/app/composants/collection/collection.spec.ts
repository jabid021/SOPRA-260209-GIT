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

    expect(component.errorMessage).toBe('Impossible de charger les collections.');
  });

  // ── Ajout ──────────────────────────────────────────────────────────────

  it('should open add form and reset nom', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.newNom = 'ancien';
    component.openAddForm();
    expect(component.isAdding).toBeTrue();
    expect(component.newNom).toBe('');
  });

  it('should close add form on cancel', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    component.newNom = 'test';
    component.cancelAdd();
    expect(component.isAdding).toBeFalse();
    expect(component.newNom).toBe('');
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
    expect(component.successMessage).toBeTruthy();
  });

  it('should not submit add if nom is blank', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    component.newNom = '   ';
    component.submitAdd();
    httpMock.expectNone('/api/collection');
  });

  it('should show a 409 error message when name already exists on add', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    component.newNom = 'SF';
    component.submitAdd();

    const req = httpMock.expectOne({ method: 'POST', url: '/api/collection' });
    req.flush({ message: 'Conflict' }, { status: 409, statusText: 'Conflict' });

    expect(component.errorMessage).toContain('existe déjà');
  });

  it('should show a generic error message on add failure', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.openAddForm();
    component.newNom = 'SF';
    component.submitAdd();

    const req = httpMock.expectOne({ method: 'POST', url: '/api/collection' });
    req.flush('Erreur', { status: 500, statusText: 'Internal Server Error' });

    expect(component.errorMessage).toContain("Erreur lors de l'ajout");
  });

  // ── Édition ────────────────────────────────────────────────────────────

  it('should start edit mode for a collection', () => {
    httpMock.expectOne('/api/collection').flush([]);
    const collection = { id: 1, nom: 'SF' };
    component.startEdit(collection);

    expect(component.editingId).toBe(1);
    expect(component.editingNom).toBe('SF');
  });

  it('should cancel edit and reset state', () => {
    httpMock.expectOne('/api/collection').flush([]);
    component.editingId = 1;
    component.editingNom = 'SF';
    component.cancelEdit();

    expect(component.editingId).toBeNull();
    expect(component.editingNom).toBe('');
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
    expect(component.successMessage).toBeTruthy();
  });

  it('should not submit edit if nom is blank', () => {
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    const collection = component.collections[0];
    component.startEdit(collection);
    component.editingNom = '   ';
    component.submitEdit(collection);
    httpMock.expectNone('/api/collection/1');
  });

  it('should show a 409 error on edit when name already exists', () => {
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    const collection = component.collections[0];
    component.startEdit(collection);
    component.editingNom = 'Jeunesse';
    component.submitEdit(collection);

    const req = httpMock.expectOne({ method: 'PUT', url: '/api/collection/1' });
    req.flush({ message: 'Conflict' }, { status: 409, statusText: 'Conflict' });

    expect(component.errorMessage).toContain('déjà utilisé');
  });

  it('should show a generic error on edit failure', () => {
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    const collection = component.collections[0];
    component.startEdit(collection);
    component.editingNom = 'Thriller';
    component.submitEdit(collection);

    const req = httpMock.expectOne({ method: 'PUT', url: '/api/collection/1' });
    req.flush('Erreur', { status: 500, statusText: 'Internal Server Error' });

    expect(component.errorMessage).toContain('Erreur lors de la modification');
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
    expect(component.successMessage).toBeTruthy();
  });

  it('should not delete if user cancels confirm', () => {
    spyOn(window, 'confirm').and.returnValue(false);
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    component.deleteCollection(component.collections[0]);

    httpMock.expectNone('/api/collection/1');
    expect(component.collections.length).toBe(1);
  });

  it('should show an error message on delete failure', () => {
    spyOn(window, 'confirm').and.returnValue(true);
    httpMock.expectOne('/api/collection').flush([{ id: 1, nom: 'SF' }]);
    fixture.detectChanges();

    component.deleteCollection(component.collections[0]);

    const req = httpMock.expectOne({ method: 'DELETE', url: '/api/collection/1' });
    req.flush('Erreur', { status: 500, statusText: 'Internal Server Error' });

    expect(component.collections.length).toBe(1);
    expect(component.errorMessage).toContain('Erreur lors de la suppression');
  });
});
