import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { HomeComponent } from './home/home.component';
import { ListBookingComponent } from './list-booking/list-booking.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';

const routes: Routes = [

  { path: '', redirectTo: 'home', pathMatch: 'full' },  
  { path: 'home', component: HomeComponent }, 
  { path: 'list-bookings', component: ListBookingComponent },
  { path: 'home/create', component: CreateBookingComponent  }, 
  { path: 'list-bookings/updateBooking/:id', component: UpdateBookingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
