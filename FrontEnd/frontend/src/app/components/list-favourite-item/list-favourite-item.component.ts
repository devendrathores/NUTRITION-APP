import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Food } from 'src/app/model/Food';
import { FavouriteService } from 'src/app/services/favourite.service';


@Component({
  selector: 'app-list-favourite-item',
  templateUrl: './list-favourite-item.component.html',
  styleUrls: ['./list-favourite-item.component.css'],
})
export class ListFavouriteItemComponent {
  expandToggleFlag = false;
  // foodArray:Food[]=new FoodUtill().convertToFoodItem(result);
  foodArray: Food[] | undefined;
  errorMsg: String | undefined;
  deletedFoodItm: Food | undefined;

  constructor(private service: FavouriteService,private snack:MatSnackBar) {
    this.fetchAllFoodItems();
    console.log(this.foodArray);
  }

  expandToggle() {
    this.expandToggleFlag = !this.expandToggleFlag;
    console.log(this.expandToggleFlag);
    return this.expandToggleFlag;
  }
  fetchAllFoodItems() {
    const observer = {
      next: (result: Food[]) => {
        this.foodArray = result;
        console.log(this.foodArray);
      },
      error: (error: HttpErrorResponse) => {
        this.errorMsg = error.error;
        console.log(this.errorMsg);
        if(this.errorMsg=="No Food found"){
          this.snack.open("Basket is empty. Please mark some food as favourite","",{duration:1500});
          setTimeout(function(){ window.location.href="/food";},1500);
        }
        
      },
    };
    const observable: Observable<Food[]> = this.service.getFoodList();
    observable.subscribe(observer);
  }

  removeFoodItem(foodItem: Food) {
    this.snack.open("Food is removed from Successfully !!  ","",{duration:1000});
    console.log(foodItem);
    
    const observer = {
      next: (result: Food) => {
        this.deletedFoodItm = result;
        this.fetchAllFoodItems();
      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Food> =
      this.service.removeItemFromFavourite(foodItem);
    observable.subscribe(observer);
  }
}
