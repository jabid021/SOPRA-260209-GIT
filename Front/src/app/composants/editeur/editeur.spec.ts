import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Editeur } from './editeur';

describe('Editeur', () => {
  let component: Editeur;
  let fixture: ComponentFixture<Editeur>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Editeur],
    }).compileComponents();

    fixture = TestBed.createComponent(Editeur);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
