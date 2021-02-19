import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Person } from '../classes/person';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private baseUrl = 'http://localhost:8080/api/person/list';

  constructor(private http: HttpClient) {}

  getAllPersonList(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.baseUrl}`);
  }

  getPersonList(personalId: string, dateOfBirth: string): Observable<Person[]> {
    return this.http.get<Person[]>(
      `${this.baseUrl}?personalId=${personalId}&dateOfBirth=${dateOfBirth}`
    );
  }
}
