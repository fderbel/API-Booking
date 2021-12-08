import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {DataTablesModule} from 'angular-datatables';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { ListBookingComponent } from './list-booking/list-booking.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UpdateBookingComponent } from './update-booking/update-booking.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ListBookingComponent,
    CreateBookingComponent,
    NavBarComponent,
    UpdateBookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule ,
    DataTablesModule ,
    NgbModule

  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ UpdateBookingComponent ]

})
export class AppModule { }
