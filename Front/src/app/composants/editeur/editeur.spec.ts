import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditeurComponent } from './editeur';

describe('Editeur', () => {
  let component: EditeurComponent;
  let fixture: ComponentFixture<EditeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditeurComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EditeurComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
