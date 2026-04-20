import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcBold } from './ajc-bold';

describe('AjcBold', () => {
  let component: AjcBold;
  let fixture: ComponentFixture<AjcBold>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcBold],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcBold);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
