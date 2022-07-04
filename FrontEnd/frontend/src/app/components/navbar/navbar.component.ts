import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { FooditemsService } from 'src/app/services/fooditems.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public loginService:LoginService,private router:Router,private route:ActivatedRoute,private snack:MatSnackBar,private foodservice:FooditemsService) { }

  ngOnInit(): void {}

  public logout(){
    this.loginService.logout();
    this.snack.open("logout Successfully !!","");
    window.location.reload();
  }
  public currentUrl(){
    return this.router.url;
  }

 
  getQuerry(querry:any)
  {
    this.foodservice.setQuerry(querry);
    this.router.routeReuseStrategy.shouldReuseRoute=()=>false;
    this.router.onSameUrlNavigation='reload';
    this.router.navigate(['/food'],{
      relativeTo:this.route
    })
  }

}
