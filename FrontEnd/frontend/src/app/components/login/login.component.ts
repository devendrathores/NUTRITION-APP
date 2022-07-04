import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService,private snack:MatSnackBar) { }

  //binding data of login form in this user variable
  public loginData={
    username:"",
    password:""
  }

  ngOnInit(): void {
  }

  loginFormSubmit(){
    console.log(this.loginData);
    if(this.loginData.username.trim()=="" || this.loginData.username==null){this.snack.open("Username is required","ok");return;}
    if(this.loginData.password.trim()=="" || this.loginData.password==null){this.snack.open("Password is required","ok");return;}

   
    /*This method will call once the user submit the form and 
    this method will call the getToken function from LoginService class, where 
    it will hit the backened or send the request to the backened(springboot)
    */
   //request to server to generate token
    this.loginService.generateToken(this.loginData).subscribe(
      (data:any)=>{// for success
        console.log("User Loged in Successfully");
        console.log(data);

        //login...
        this.loginService.setToken(data.token);
        //will get the session for current user
        this.loginService.getCurrentUser().subscribe(
          (user:any)=>{
            this.loginService.setUser(user);
            console.log(user);
            //redirect....to food Module
            window.location.href="/home";
          }
        )
      },
      (error)=>{//for in case of error
         console.log(error);
         this.snack.open("Invalid Details !! Try again","ok");
      }
    );
  }

}
