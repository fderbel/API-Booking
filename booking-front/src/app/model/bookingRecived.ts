export class BookingRecived{

    id !:String;
    checkInDate!: Date;
	checkOutDate!: Date;
   
    constructor( checkInDate: Date, checkOutDate: Date, createdDate: Date,updatedDate: Date) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
       
    }
}
