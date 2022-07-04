import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFooditemsComponent } from './list-fooditems.component';

describe('ListFooditemsComponent', () => {
  let component: ListFooditemsComponent;
  let fixture: ComponentFixture<ListFooditemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListFooditemsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListFooditemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
