import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { BookingService } from '../service/booking.service';
import { BookingRecived } from '../model/bookingRecived';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UpdateBookingComponent } from '../update-booking/update-booking.component';

@Component({
  selector: 'app-list-booking',
  templateUrl: './list-booking.component.html',
  styleUrls: ['./list-booking.component.css']
})
export class ListBookingComponent implements OnInit {
  usersArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  bookings: BookingRecived[] = [];
  deleteMessage = false;
  
  constructor(private fb: FormBuilder,private bookingservice: BookingService,private modalService: NgbModal) {}

  ngOnInit() {
    this.listBooking();
  }
  listBooking() {
    this.bookingservice.getBookingsList().subscribe(data => {
      this.bookings = data;
    })
  }
  delete(id: any) {
    if (confirm("Are you sure to delete ")) {

      this.bookingservice.deleteBookingById(id)
        .subscribe(
          response => {
            this.listBooking();
          });
    }
  }
 
  open(booking : BookingRecived) {
    const modalRef = this.modalService.open(UpdateBookingComponent);
    modalRef.componentInstance.bookingrecived = booking;
    modalRef.componentInstance.passEntry.subscribe(() => {
      this.listBooking();
      })
    }
}


