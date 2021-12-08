import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) { }

  getBookingsList(): Observable<any> {
    return this.http.get(environment.api_url + '/api/bookings');
  }
  getBookingById(id: any): Observable<any> {
    return this.http.get(environment.api_url + `/api/bookings/${id}`);
  }
  deleteBookingById(id: any): Observable<any> {
    return this.http.delete(environment.api_url + `/api/bookings/delete/${id}`);
  }

  createBooking(data: any): Observable<any> {
    return this.http.post(environment.api_url + `/api/bookings/create`, data);
  }
  updateBooking(id: any, data: any): Observable<any> {
    return this.http.put(environment.api_url + `/api/bookings/update/${id}`, data);
  }

}
