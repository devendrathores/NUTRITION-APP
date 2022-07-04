import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Food } from 'src/app/model/Food';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';


@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  
  constructor(private client:HttpClient,private loginService:LoginService) { }

  //getting favourite food list
  getFoodList():Observable<Food[]>{
    const url="http://localhost:9000/food/"+this.loginService.getUser().username;
    const observable:Observable<Food[]>=this.client.get<Food[]>(url);
    return observable;
  }

   //deleting favourite food for user
  removeItemFromFavourite(food:Food):Observable<Food>{
    const username=food.username;
    const fdcId=food.fdcId;
    const url="http://localhost:9000/food/delete/"+username+"/"+fdcId;
    const observable:Observable<Food>=this.client.delete<Food>(url);
    return observable;

  }

}
