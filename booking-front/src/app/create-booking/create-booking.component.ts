import { Component} from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { BookingCreated } from '../model/bookingCreated';
import { BookingService } from '../service/booking.service';
@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent  {

  datebegin!: NgbDateStruct;
  dateend!: NgbDateStruct;
  booking !: BookingCreated;
  errormessage !: string;
  error = false;
  submitted = false;
  constructor(private bookingservice: BookingService) { }



  createbooking(): void {
    this.booking = new BookingCreated(new Date(this.datebegin.year, this.datebegin.month - 1, this.datebegin.day + 1),
      new Date(this.dateend.year, this.dateend.month - 1, this.dateend.day + 1),
      new Date(),
      new Date());
    this.bookingservice.createBooking(this.booking)
      .subscribe(
        response => {
          this.error = false;
          this.submitted = true;
        },
        error => {
          this.error = true;
          console.log(error);
          this.errormessage = error.error.message
        });
  }
}
