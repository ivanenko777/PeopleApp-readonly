import { Component, OnInit } from '@angular/core';
import { Person } from '../../classes/person';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css'],
})
export class PersonComponent implements OnInit {
  personalId: string;
  dateOfBirth: string;
  personList: Person[];
  message: string;

  constructor(private personService: PersonService) {
    this.personalId = '';
    this.dateOfBirth = '';
    this.personList = [];
    this.message = '';
  }

  ngOnInit(): void {
    this.getPersonList();
  }

  findPerson(personalId: string, dateOfBirth: string) {
    this.personalId = personalId;
    this.dateOfBirth = dateOfBirth;
    this.personList = [];
    this.getPersonList();
    return false;
  }

  getPersonList(): void {
    this.personService
      .getPersonList(this.personalId, this.dateOfBirth)
      .subscribe(
        (data: Person[]) => {
          // console.log(data);
          this.personList = data;
          this.message = '';
          if (!this.personList.length) {
            this.message = 'Person is not found!';
          }
        },
        (error) => {
          console.error('error caught in component');
          this.message = error.error;
        }
      );
  }
}
