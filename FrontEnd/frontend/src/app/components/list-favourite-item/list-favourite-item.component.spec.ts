import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFavouriteItemComponent } from './list-favourite-item.component';

describe('ListFavouriteItemComponent', () => {
  let component: ListFavouriteItemComponent;
  let fixture: ComponentFixture<ListFavouriteItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListFavouriteItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListFavouriteItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
