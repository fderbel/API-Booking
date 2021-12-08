import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { BookingCreated } from '../model/bookingCreated';
import { BookingRecived } from '../model/bookingRecived';
import { BookingService } from '../service/booking.service';

@Component({
  selector: 'app-update-booking',
  templateUrl: './update-booking.component.html',
  styleUrls: ['./update-booking.component.css']
})
export class UpdateBookingComponent implements OnInit {
 
  @Input ()bookingrecived !: BookingRecived;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  datebegin!: NgbDateStruct;
  dateend!: NgbDateStruct;
  booking !: BookingCreated;
  errormessage !: string;
  error = false;
  
  constructor(  private bookingservice: BookingService, public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
   var datebegin = new Date (this.bookingrecived.checkInDate)
    this.datebegin = { day: datebegin.getDate(), month: datebegin.getUTCMonth()+1, year: datebegin.getUTCFullYear()};
    var dateend = new Date (this.bookingrecived.checkOutDate)
    this.dateend = { day: dateend.getDate(), month: dateend.getUTCMonth()+1, year: dateend.getUTCFullYear()};
   
  }
  updatebooking(): void {
    this.booking = new BookingCreated(new Date(this.datebegin.year, this.datebegin.month - 1, this.datebegin.day + 1),
      new Date(this.dateend.year, this.dateend.month - 1, this.dateend.day + 1),
      new Date(),
      new Date());
    this.bookingservice.updateBooking(this.bookingrecived.id, this.booking)
      .subscribe(
        response => {
          this.error = false;
          this.activeModal.close();
          this.passEntry.emit(response);
        },
        error => {
          this.error = true;
          this.errormessage = error.error.message
        });
  }
}
