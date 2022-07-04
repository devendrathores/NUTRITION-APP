import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ListFavouriteItemComponent } from './components/list-favourite-item/list-favourite-item.component';
import { ListFooditemsComponent } from './components/list-fooditems/list-fooditems.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { DummyGuardGuard } from './services/dummy-guard.guard';

const routes: Routes = [
  {
    path:'',
    redirectTo:'/login',
    pathMatch:'full',
  },
  {
    path:'home',
    component:HomeComponent,
    pathMatch:"full",
    canActivate:[DummyGuardGuard]

  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full',
  },
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:'full',
  },
  {
    path:'food',
    component:ListFooditemsComponent,
    pathMatch:'full',
    canActivate:[DummyGuardGuard]
  },
  {
    path:'favourite',
    component:ListFavouriteItemComponent,
    pathMatch:'full',
    canActivate:[DummyGuardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
