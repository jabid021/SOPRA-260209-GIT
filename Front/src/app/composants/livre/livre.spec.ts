import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Livre } from './livre';

describe('Livre', () => {
  let component: Livre;
  let fixture: ComponentFixture<Livre>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Livre],
    }).compileComponents();

    fixture = TestBed.createComponent(Livre);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
