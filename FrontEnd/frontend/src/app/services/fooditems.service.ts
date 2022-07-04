import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from 'src/app/model/Food';



@Injectable({
  providedIn: 'root'
})
export class FooditemsService {
  //for searchQuery
  querry:string="";
  pageNo:number=0;
  constructor(private http:HttpClient) { }
 //for searchQuery
  setQuerry(querry:any)
  {
    this.querry=querry;
    console.log(this.querry);
    //this.getFoodList();
  }
  
  //getting all food api from backened
  getFoodList():Observable<Food[]>{
    let url="http://localhost:9000/api/food";
    if(this.querry!==""){
      url="http://localhost:9000/api/"+this.querry+"/"+this.pageNo;
    }
    const observable:Observable<Food[]>=this.http.get<Food[]>(url);
    return observable;
    
  }
//getting userDetails
public getUser(){
  let userStr=localStorage.getItem("user");
  if(userStr!=null){return JSON.parse(userStr);}
    else{alert("User doesn't exist");return ""}

}

    //adding food in the favourite list
  addToFavourite(requestdata:Food):Observable<Food>{
    requestdata.username=this.getUser().username;
    const url="http://localhost:9000/food/add";
    const observable:Observable<Food>=this.http.post<Food>(url, requestdata);
    console.log(requestdata);
    console.log("food added");
    return observable;

  }





}
