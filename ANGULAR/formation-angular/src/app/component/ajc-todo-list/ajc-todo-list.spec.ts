import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcTodoList } from './ajc-todo-list';

describe('AjcTodoList', () => {
  let component: AjcTodoList;
  let fixture: ComponentFixture<AjcTodoList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcTodoList],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcTodoList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
