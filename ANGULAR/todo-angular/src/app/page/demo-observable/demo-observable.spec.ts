import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoObservable } from './demo-observable';

describe('DemoObservable', () => {
  let component: DemoObservable;
  let fixture: ComponentFixture<DemoObservable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemoObservable],
    }).compileComponents();

    fixture = TestBed.createComponent(DemoObservable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
