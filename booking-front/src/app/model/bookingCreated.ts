export class BookingCreated{

    checkInDate!: Date;
	checkOutDate!: Date;
    createdDate!: Date;
    updatedDate!: Date;

    constructor( checkInDate: Date, checkOutDate: Date, createdDate: Date,updatedDate: Date) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
