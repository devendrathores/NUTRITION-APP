import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SignupService } from 'src/app/services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private signupService:SignupService,private snack:MatSnackBar) { }

//binding data of signup form in this user variable
  public user={
    username:"",
    password:"",
    firstname:"",
    lastname:"",
    email:"",
    phone:""
  }

  ngOnInit(): void {
  }

  formSubmit(){
    console.log(this.user);
    if(this.user.username=="" || this.user.username==null){ this.snack.open("username is required !! ","ok"); return;}
    if(this.user.password=="" || this.user.password==null){this.snack.open("password is required !! ","ok"); return;}
    if(this.user.firstname=="" || this.user.firstname==null){this.snack.open("firstname is required !! ","ok"); return;}
    if(this.user.lastname=="" || this.user.lastname==null){this.snack.open("lastname is required !! ","ok"); return;}
    if(this.user.email=="" || this.user.email==null){this.snack.open("email is required !! ","ok"); return;}
    if(this.user.phone=="" || this.user.phone==null){this.snack.open("phone is required !! ","ok"); return;}

   
    /*This method will call once the user submit the form and 
    this method will call the addUser function from SignupService class, where 
    it will hit the backened or send the request to the backened(springboot)
    */
    this.signupService.addUser(this.user).subscribe(
      (data)=>{// for success
        console.log(data);
        this.snack.open("You are registered Successfully!!  ","",{duration:2000});
        window.location.href="/login";
      },
      (error)=>{//for in case of error
         console.log(error);
         this.snack.open("Username already exists.!! Please use a different one","ok");
      }
    )
  }
}
