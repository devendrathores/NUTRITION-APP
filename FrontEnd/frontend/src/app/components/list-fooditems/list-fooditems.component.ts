import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Food } from 'src/app/model/Food';
import { FooditemsService } from 'src/app/services/fooditems.service';


@Component({
  selector: 'app-list-fooditems',
  templateUrl: './list-fooditems.component.html',
  styleUrls: ['./list-fooditems.component.css']
})
export class ListFooditemsComponent {

  expandToggleFlag = false;
  // foodArray:Food[]=new FoodUtill().convertToFoodItem(result);
  foodArray: Food[] | undefined;
  errorMsg: String | undefined;
  addedFood:Food|undefined;

  constructor(private service: FooditemsService,private router:Router,private route:ActivatedRoute,private snack:MatSnackBar) {
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
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Food[]> = this.service.getFoodList();
    observable.subscribe(observer);
  }

  addToFavourite(foodItem: Food) {
    this.snack.open("Food Added Successfully !! ","",{duration:1000});
    console.log(foodItem);
    
    const observer = {
      next: (result: Food) => {
        this.addedFood = result;
       
      },
      error: (error: HttpErrorResponse) => {
        this.errorMsg = error.error;
        console.log(this.errorMsg);
        if(this.errorMsg=="Food is already in the favourite list"){
          this.snack.open("Sorry!! Food is already in your favourite list","",{duration:1000});
        }
      },
    };
    const observable: Observable<Food> =
      this.service.addToFavourite(foodItem);
    observable.subscribe(observer);
  }

  
  getNext()
  {
    if(this.service.pageNo<8)
     this.service.pageNo++;
     this.router.routeReuseStrategy.shouldReuseRoute=()=>false;
    this.router.onSameUrlNavigation='reload';
    this.router.navigate(['/food'],{
      relativeTo:this.route
    })
     console.warn(this.service.pageNo);
  }
  getPrev()
  {
     if(this.service.pageNo>0)
     this.service.pageNo--;
     this.router.routeReuseStrategy.shouldReuseRoute=()=>false;
    this.router.onSameUrlNavigation='reload';
    this.router.navigate(['/food'],{
      relativeTo:this.route
    })
     //console.warn(this.service.pageNo);
  }

}
